package com.epam.training.sportsbeatting.facade;

import com.epam.training.sportsbeatting.converter.Converter;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import com.epam.training.sportsbeatting.dto.user.read.UserDto;
import com.epam.training.sportsbeatting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<User, UserDto> userConverter;

    @Autowired
    private Converter<CreateUserDto, User> createUserDtoConverter;

    @Override
    public UserDto getProfile() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userConverter.convert(principal);
    }

    @Override
    public UserDto registerUser(CreateUserDto userDto) {
        User user = createUserDtoConverter.convert(userDto);
        return userConverter.convert(userService.register(user));
    }
}
