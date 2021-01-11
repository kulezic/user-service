package com.a2.userservice.controller;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.dto.*;
import com.a2.userservice.secutiry.CheckSecurity;
import com.a2.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
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

//    @ApiOperation(value = "Get all users")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                    value = "Sorting criteria in the format: property(,asc|desc). " +
//                            "Default sort order is ascending. " +
//                            "Multiple sort criteria are supported.")})
//    @GetMapping
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
//    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                     Pageable pageable) {
//
//        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
//    }
    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        System.out.println(id + " DISCOUNT");
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAdmins() {
        return new ResponseEntity<>(userService.getAdmin(), HttpStatus.OK);
    }
    /*@PostMapping("/{id}/cancelflight")
    public ResponseEntity<DiscountDto> cancelFlight(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.cancelFlight(id), HttpStatus.OK);
    }*/

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {
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
    public ResponseEntity<CardDto> addCardUser(@RequestHeader("Authorization") String authorization,@RequestBody @Valid CreateCardDto createCardDto) {
        return new ResponseEntity<>(userService.addCard(createCardDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }
    @ApiOperation(value = "Login admin")
    @PostMapping("/admin/login")
    public ResponseEntity<TokenResponseDto> loginAdmin(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.loginAdmin(tokenRequestDto), HttpStatus.OK);
    }
}

