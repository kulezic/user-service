package com.a2.userservice.service;

import com.a2.userservice.domain.User;
import com.a2.userservice.domain.UserRank;
import com.a2.userservice.dto.DiscountDto;
import com.a2.userservice.dto.TokenRequestDto;
import com.a2.userservice.dto.TokenResponseDto;
import com.a2.userservice.exception.NotFoundException;
import com.a2.userservice.repository.CardRepository;
import com.a2.userservice.repository.RoleRepository;
import com.a2.userservice.repository.UserRankRepository;
import com.a2.userservice.repository.UserRepository;
import com.a2.userservice.secutiry.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    private TokenService tokenService;
    private UserRepository userRepository;
    private UserRankRepository userRankRepository;
    private CardRepository cardRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository, UserRankRepository userRankRepository, CardRepository cardRepository, RoleRepository roleRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userRankRepository = userRankRepository;
        this.cardRepository = cardRepository;
        this.roleRepository = roleRepository;
    }

    public DiscountDto findDiscount(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        List<UserRank> userRankList = userRankRepository.findAll();
        //get discount
        Integer discount = userRankList.stream()
                .filter(userRank -> userRank.getMaxNumberOfMiles() >= user.getMiles()
                        && userRank.getMinNumberOfMiles() <= user.getMiles())
                .findAny()
                .get()
                .getDiscount();
        return new DiscountDto(discount);
    }

    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        User user = userRepository
                .findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
