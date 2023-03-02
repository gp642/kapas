package com.kapas.user.repository;

import com.kapas.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByUserName(String userName);

    @Query("select u from User u join fetch u.role where u.id = :id")
    User findUserById(Integer id);
}