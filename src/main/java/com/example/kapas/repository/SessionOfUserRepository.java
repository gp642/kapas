package com.example.kapas.repository;

import com.example.kapas.entity.SessionsOfUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionOfUserRepository extends CrudRepository<SessionsOfUser, Integer> {

    @Query("delete from SessionsOfUser s where s.expiresAt <= now()")
    @Modifying
    void deleteAllExpiredSessions();
}
