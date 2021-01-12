package com.a2.userservice.controller;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.dto.*;
import com.a2.userservice.secutiry.CheckSecurity;
import com.a2.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        System.out.println(id + " DISCOUNT");
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAdmins() {
        return new ResponseEntity<>(userService.getAdmin(), HttpStatus.OK);
    }


    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<UserDto> updateUser(@RequestHeader("Authorization") String authorization,@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.addUser(userCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id ) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/addcard")
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<CardDto> addCardUser(@RequestHeader("Authorization") String authorization,@RequestBody CreateCardDto createCardDto) {
        System.out.println(createCardDto.getCardHolderName() + " " + createCardDto.getCardNumber() + " " + createCardDto.getCif());
        return new ResponseEntity<>(userService.addCard(createCardDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<TokenResponseDto> loginAdmin(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.loginAdmin(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping("/rank/{id}")
    public ResponseEntity<UserRankDto> getUserRank(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findUserRank(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<CardDto>>  getUserCards(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserCards(id), HttpStatus.OK);
    }
}

