package com.a2.userservice.repository;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.domain.Card;
import com.a2.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUserId(Long userId);
}
