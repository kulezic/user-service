package com.a2.userservice.repository;

import com.a2.userservice.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findUserByUsernameAndPassword(String username, String password);

}
