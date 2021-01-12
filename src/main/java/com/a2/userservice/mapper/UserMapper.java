package com.a2.userservice.mapper;

import com.a2.userservice.domain.Card;
import com.a2.userservice.domain.User;
import com.a2.userservice.dto.CardDto;
import com.a2.userservice.dto.UserCreateDto;
import com.a2.userservice.dto.UserDto;
import com.a2.userservice.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private RoleRepository roleRepository;
    private CardMapper cardMapper;

    public UserMapper(RoleRepository roleRepository, CardMapper cardMapper) {
        this.roleRepository = roleRepository;
        this.cardMapper = cardMapper;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassportNo(user.getPassportNo());
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card c : user.getCards()){
            cardDtoList.add(cardMapper.cardToCardDto(c));
        }
        userDto.setCardDtos(cardDtoList);
        return userDto;
    }

    public User userCreateDtoToUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPassword(userCreateDto.getPassword());
        user.setPassportNo(userCreateDto.getPassportNo());
        user.setMiles(0);
        user.setCards(new ArrayList<>());
        user.setRole(roleRepository.findRoleByName("ROLE_USER").get());
        return user;
    }
}
