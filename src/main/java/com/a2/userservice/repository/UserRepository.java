package com.a2.userservice.repository;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.email like :email")
    Optional<User> findUserByEmail(String email);
}
