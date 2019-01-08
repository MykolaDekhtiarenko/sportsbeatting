package com.epam.training.sportsbeatting.facade;

import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import com.epam.training.sportsbeatting.dto.user.read.UserDto;

public interface UserFacade {
    UserDto getProfile();

    UserDto registerUser(CreateUserDto userDto);
}
