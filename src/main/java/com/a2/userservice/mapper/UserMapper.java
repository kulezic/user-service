package com.a2.userservice.mapper;

import com.a2.userservice.domain.User;
import com.a2.userservice.dto.UserCreateDto;
import com.a2.userservice.dto.UserDto;
import com.a2.userservice.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassportNo(user.getPassportNo());
        return userDto;
    }

    public User userCreateDtoToUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepository.findRoleByName("ROLE_USER").get());

        //TODO ako treba za karticu
        return user;
    }
}
