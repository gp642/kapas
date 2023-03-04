package com.kapas.vendor.repository;

import com.kapas.vendor.entity.Vendor;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends BaseJpaRepository<Vendor, Integer>, JpaSpecificationExecutor<Vendor> {
    @Query(value = "select v from Vendor v join fetch v.vendorType join fetch v.idType where v.id = :vendorId")
    Optional<Vendor> findByIdAndFetchVendorTypeAndIdType(Integer vendorId);

    @Query(value = "delete from Vendor v where v.id = :vendorId")
    Optional<Vendor> findByIdAndDelete(Integer vendorId);
}
