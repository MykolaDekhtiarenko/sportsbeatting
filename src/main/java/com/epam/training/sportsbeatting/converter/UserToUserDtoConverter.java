package com.epam.training.sportsbeatting.converter;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.dto.UserDto;

public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .accountNumber(user.getAccountNumber())
                .dateOfBirth(user.getDateOfBirth())
                .balance(user.getBalance())
                .currency(user.getCurrency())
                .build();
    }
}
