package com.a2.userservice.service.impl;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.domain.Card;
import com.a2.userservice.domain.User;
import com.a2.userservice.domain.UserRank;
import com.a2.userservice.dto.*;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    private JavaMailSender emailSender;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository,
                           UserRankRepository userRankRepository, CardRepository cardRepository,
                           RoleRepository roleRepository, AdminRepository adminRepository,
                           UserMapper userMapper, CardMapper cardMapper, JavaMailSender emailSender) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userRankRepository = userRankRepository;
        this.cardRepository = cardRepository;
        this.roleRepository = roleRepository;
        this.adminRepository = adminRepository;
        this.userMapper = userMapper;
        this.cardMapper = cardMapper;
        this.emailSender = emailSender;
    }

    public DiscountDto findDiscount(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        List<UserRank> userRankList = userRankRepository.findAll();
        BigDecimal discount = BigDecimal.valueOf(userRankList.stream()
                .filter(userRank -> userRank.getMaxNumberOfMiles() >= user.getMiles()
                        && userRank.getMinNumberOfMiles() <= user.getMiles())
                .findAny()
                .get()
                .getDiscount());
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
    public void cancelTicket(TicketCancelDto ticketCancelDto) {
         User user = userRepository.findById(ticketCancelDto.getUserId())
                 .orElseThrow(() -> new NotFoundException(String
                 .format("User with id: %d not found.", ticketCancelDto.getUserId())));
         user.setMiles(user.getMiles() - ticketCancelDto.getMiles());
         userRepository.save(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(user.getEmail());
        message.setSubject("Flight canceled");
        message.setText("Your flight is canceled for ticket");
        emailSender.send(message);
    }

    @Override
    public List<Admin> getAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).
            orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", id)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserRankDto findUserRank(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", id)));
        List<UserRank> userRankList = userRankRepository.findAll();
        String rankName = userRankList.stream()
                .filter(userRank -> userRank.getMaxNumberOfMiles() >= user.getMiles()
                        && userRank.getMinNumberOfMiles() <= user.getMiles())
                .findAny()
                .get()
                .getName();
        Long miles = user.getMiles();
        return new UserRankDto(rankName, miles);
    }

    @Override
    public List<CardDto> getUserCards(Long id) {
        return cardRepository.findAllByUserId(id).stream().map(cardMapper::cardToCardDto).collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        userRepository.save(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk2021test@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Registration successful");
        message.setText("Welcome "+user.getFirstName()+" "+user.getLastName()+" to flight and ticket service.");
        emailSender.send(message);
        return userMapper.userToUserDto(user);
    }

    @Override
    public CardDto addCard(CreateCardDto createCardDto) {
        Card card = cardMapper.cardCreateDtoToCard(createCardDto);
        User user = userRepository.findById(createCardDto.getUserId()).
                orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", createCardDto.getUserId())));
        card.setUser(user);
        cardRepository.save(card);
        CardDto cardDto = cardMapper.cardToCardDto(card);
        return cardDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId()).
                orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", userDto.getUserId())));
        if(!user.getEmail().equals(userDto.getEmail())){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("sk2021test@gmail.com");
            message.setTo(userDto.getEmail());
            message.setSubject("Mail changed");
            message.setText("Welcome "+userDto.getFirstName()+" "+userDto.getLastName()+" to flight and ticket service.");
            emailSender.send(message);
        }
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassportNo(userDto.getPassportNo());
        return  userMapper.userToUserDto(user);
    }

    @Override
    public void incrementMiles(IncrementMilesDto incrementMilesDto) {
        User user = userRepository.findById(incrementMilesDto.getUserId()).
                orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", incrementMilesDto.getUserId())));
        user.setMiles(user.getMiles() + incrementMilesDto.getMiles());
        userRepository.save(user);
    }
}
