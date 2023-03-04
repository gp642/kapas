package com.kapas.vendor.service;

import com.kapas.user.entity.User;
import com.kapas.vendor.entity.Vendor;
import com.kapas.vendor.entity.Vendor_;
import com.kapas.vendor.mapper.VendorMapper;
import com.kapas.vendor.model.PaginatedResponse;
import com.kapas.vendor.model.VendorRequest;
import com.kapas.vendor.model.VendorResponse;
import com.kapas.vendor.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    private Vendor checkVendor(Integer vendorId) throws Exception {
        Optional<Vendor> optionalVendor = vendorRepository.findByIdAndFetchVendorTypeAndIdType(vendorId);
        return optionalVendor.orElseThrow(() -> new Exception("Vendor Not Found"));
    }

    @Transactional
    public VendorResponse createVendor(VendorRequest vendorRequest, User user) {
        Vendor vendor = vendorMapper.vendorRequestToVendor(vendorRequest, user);
        vendor = vendorRepository.persist(vendor);
        return vendorMapper.vendorToVendorResponse(vendor);
    }

    @Transactional(readOnly = true)
    public VendorResponse getVendor(Integer vendorId) throws Exception {
        Optional<Vendor> optionalVendor = vendorRepository.findByIdAndFetchVendorTypeAndIdType(vendorId);
        Vendor vendor = optionalVendor.orElseThrow(() -> new Exception("Vendor Not Found"));
        return vendorMapper.vendorToVendorResponse(vendor);
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<VendorResponse> getAllVendors(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Specification<Vendor> specification = (vendor, query, cb) ->  {
            vendor.fetch(Vendor_.vendorType, JoinType.INNER);
            vendor.fetch(Vendor_.idType, JoinType.INNER);
            return cb.conjunction();
        };
        Page<Vendor> vendors = vendorRepository.findAll(specification, pageable);
        return vendorMapper.vendorToVendorResponse(vendors);
    }

    @Transactional
    public void deleteVendor(Integer vendorId) throws Exception {
        Vendor vendor = checkVendor(vendorId);
        vendorRepository.delete(vendor);
    }

    @Transactional
    public VendorResponse updateVendor(VendorRequest vendorRequest, User user, Integer vendorId) throws Exception {
        Vendor vendor = checkVendor(vendorId);
        vendorMapper.updatedVendorRequestToVendor(vendorRequest, user, vendor);
        vendor = vendorRepository.merge(vendor);
        return vendorMapper.vendorToVendorResponse(vendor);
    }
}
