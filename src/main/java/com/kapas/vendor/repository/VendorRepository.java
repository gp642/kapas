package com.kapas.vendor.repository;

import com.kapas.vendor.entity.Vendor;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends BaseJpaRepository<Vendor, Integer>, JpaSpecificationExecutor<Vendor> {
    @Query(value = "select v from Vendor v join fetch v.vendorType join fetch v.idType where v.id = :vendorId")
    Optional<Vendor> findByIdAndFetchVendorTypeAndIdType(@Param("vendorId") Integer vendorId);

    @Query(value = "select v from Vendor v where v.firstName = :firstName and v.lastName = :lastName and v.mobile = :mobile")
    Optional<Vendor> findByNameAndMobile(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mobile") String mobile);
}
