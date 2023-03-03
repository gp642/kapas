package com.kapas.vendor.repository;

import com.kapas.vendor.entity.VendorType;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeRepository extends BaseJpaRepository<VendorType, Integer> {
    Optional<VendorType> findByType(String vendorType);
}
