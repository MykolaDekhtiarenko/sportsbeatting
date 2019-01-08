package com.epam.training.sportsbeatting.controller;

import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import com.epam.training.sportsbeatting.dto.user.read.UserDto;
import com.epam.training.sportsbeatting.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/me/profile")
    private UserDto getProfile() {
        return userFacade.getProfile();
    }

    @PostMapping
    private UserDto register(@Valid @RequestBody CreateUserDto userDto) {
        return userFacade.registerUser(userDto);
    }


}
