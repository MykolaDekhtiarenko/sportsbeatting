package com.epam.training.sportsbeatting.controller;

import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import com.epam.training.sportsbeatting.dto.user.read.UserDto;
import com.epam.training.sportsbeatting.dto.user.update.UpdateUserDto;
import com.epam.training.sportsbeatting.dto.wager.WagerDto;
import com.epam.training.sportsbeatting.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/me/wagers")
    private List<WagerDto> getCurrentUserWagers() {
        return userFacade.getCurrentUserWagers();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/me/wagers/{wagerId}")
    private void deleteUnprocessedWager(@PathVariable Long wagerId) {
        userFacade.deleteUnprocessedWager(wagerId);
    }

    @PutMapping
    private UserDto updateUserInfo(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return userFacade.updateUserDto(updateUserDto);
    }


}
