package com.kapas.vendor.repository;

import com.kapas.vendor.entity.IdType;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdTypeRepository extends BaseJpaRepository<IdType, Integer> {
    Optional<IdType> findByType(String idProofType);
}
