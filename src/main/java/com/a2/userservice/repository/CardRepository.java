package com.a2.userservice.repository;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.domain.Card;
import com.a2.userservice.domain.User;
import com.a2.userservice.dto.CardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findCardsByUser(User user);

    @Query("select c from Card c where c.user.id = :id")
    List<Card> getUserCards(Long id);
}
