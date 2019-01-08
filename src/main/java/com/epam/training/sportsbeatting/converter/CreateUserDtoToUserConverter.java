package com.epam.training.sportsbeatting.converter;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDtoToUserConverter implements Converter<CreateUserDto, User> {

    @Override
    public User convert(CreateUserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .accountNumber(userDto.getAccountNumber())
                .dateOfBirth(userDto.getDateOfBirth())
                .build();
    }
}
