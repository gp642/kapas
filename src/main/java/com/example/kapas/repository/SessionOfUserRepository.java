package com.example.kapas.repository;

import com.example.kapas.entity.Sessions;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionOfUserRepository extends CrudRepository<Sessions, Integer> {

    @Query("delete from Sessions s where s.expiresAt <= now()")
    @Modifying
    void deleteAllExpiredSessions();
}
