package com.epam.training.sportsbeatting.facade;

import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import com.epam.training.sportsbeatting.dto.user.read.UserDto;
import com.epam.training.sportsbeatting.dto.user.update.UpdateUserDto;
import com.epam.training.sportsbeatting.dto.wager.WagerDto;

import java.util.List;

public interface UserFacade {
    UserDto getProfile();

    UserDto registerUser(CreateUserDto userDto);

    List<WagerDto> getCurrentUserWagers();

    void deleteUnprocessedWager(Long wagerId);

    UserDto updateUserDto(UpdateUserDto updateUserDto);
}
