package com.kapas.vendor.mapper;

import com.kapas.user.entity.User;
import com.kapas.vendor.entity.IdType;
import com.kapas.vendor.entity.Vendor;
import com.kapas.vendor.entity.VendorType;
import com.kapas.vendor.model.PaginatedResponse;
import com.kapas.vendor.model.VendorRequest;
import com.kapas.vendor.model.VendorResponse;
import com.kapas.vendor.repository.IdTypeRepository;
import com.kapas.vendor.repository.VendorTypeRepository;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class VendorMapper {

    @Autowired
    private VendorTypeRepository vendorTypeRepository;
    @Autowired
    private IdTypeRepository idTypeRepository;

    @Mapping(target = "firstName", source = "vendorRequest.firstName")
    @Mapping(target = "lastName", source = "vendorRequest.lastName")
    @Mapping(target = "mobile", source = "vendorRequest.mobile")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "modificationTime", ignore = true)
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "modifiedBy", source = "user")
    public abstract Vendor vendorRequestToVendor(VendorRequest vendorRequest, User user);

    public VendorType mapVendorType(String vendorType) {
        Optional<VendorType> vendorTypeOptional = vendorTypeRepository.findByType(vendorType);
        return vendorTypeOptional.orElseThrow(() -> new RuntimeException("Vendor Type not valid"));
    }

    public IdType mapIdType(String idType) {
        Optional<IdType> idTypeOptional = idTypeRepository.findByType(idType);
        return idTypeOptional.orElseThrow(() -> new RuntimeException("Id Proof Type not valid"));
    }

    public abstract VendorResponse vendorToVendorResponse(Vendor vendor);

    public String mapVendorType(VendorType vendorType) {
        return vendorType.getType();
    }

    public String mapIdType(IdType idType) {
        return idType.getType();
    }

    public abstract PaginatedResponse<VendorResponse> vendorToVendorResponse(Page<Vendor> vendors);

    @InheritConfiguration
    public abstract void updatedVendorRequestToVendor(VendorRequest vendorRequest, User user, @MappingTarget Vendor vendor);

}
