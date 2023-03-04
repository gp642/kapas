package com.kapas.user.repository;

import com.kapas.user.entity.Sessions;
import com.kapas.user.entity.User;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SessionsRepository extends BaseJpaRepository<Sessions, Integer> {

    @Query("delete from Sessions s where s.expiresAt <= now()")
    @Modifying
    void deleteAllExpiredSessions();

    @Query(value = "select s from Sessions s join fetch s.user where s.sessionId = :sessionId")
    Optional<Sessions> findBySessionId(@Param("sessionId") String sessionId);

    @Transactional
    @Modifying
    @Query("delete from Sessions s where s.sessionId = :sessionId")
    void deleteBySessionId(@Param("sessionId") String sessionId);

    @Transactional
    @Modifying
    @Query("delete from Sessions s where s.user = :userId")
    void deleteForUser(@Param("userId") User userId);
}
