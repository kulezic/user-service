package com.a2.userservice.service;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.domain.Card;
import com.a2.userservice.domain.User;
import com.a2.userservice.domain.UserRank;
import com.a2.userservice.dto.*;
import com.a2.userservice.dto.CancelMilesDto;
import com.a2.userservice.dto.DiscountDto;
import com.a2.userservice.dto.TokenRequestDto;
import com.a2.userservice.dto.TokenResponseDto;
import com.a2.userservice.exception.NotFoundException;
import com.a2.userservice.mapper.CardMapper;
import com.a2.userservice.mapper.UserMapper;
import com.a2.userservice.repository.*;
import com.a2.userservice.secutiry.service.TokenService;
import com.a2.userservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private TokenService tokenService;
    private UserRepository userRepository;
    private UserRankRepository userRankRepository;
    private CardRepository cardRepository;
    private RoleRepository roleRepository;
    private AdminRepository adminRepository;
    private UserMapper userMapper;
    private CardMapper cardMapper;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository,
                           UserRankRepository userRankRepository, CardRepository cardRepository,
                           RoleRepository roleRepository, AdminRepository adminRepository,
                           UserMapper userMapper, CardMapper cardMapper) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userRankRepository = userRankRepository;
        this.cardRepository = cardRepository;
        this.roleRepository = roleRepository;
        this.adminRepository = adminRepository;
        this.userMapper = userMapper;
        this.cardMapper = cardMapper;
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
                        .format("User with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    public TokenResponseDto loginAdmin(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Admin user = adminRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public void cancelMiles(CancelMilesDto cancelMilesDto) {
         User user = userRepository.findById(cancelMilesDto.getUserId())
                 .orElseThrow(() -> new NotFoundException(String
                 .format("User with id: %d not found.", cancelMilesDto.getUserId())));
         user.setMiles(user.getMiles() - cancelMilesDto.getMiles());
         userRepository.save(user);
    }

    public UserDto add(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public CardDto addCard(CreateCardDto createCardDto) {
        Card card = cardMapper.cardCreateDtoToCard(createCardDto);
        cardRepository.save(card);
        CardDto cardDto = cardMapper.cardToCardDto(card);
        return cardDto;
    }

    public UserDto update(UserDto userDto) {
        return  null;
    }

//    public UserDto cancelFlight(Long id) {
//
//    }
}
