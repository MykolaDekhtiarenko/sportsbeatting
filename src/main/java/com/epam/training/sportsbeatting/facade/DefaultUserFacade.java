package com.epam.training.sportsbeatting.facade;

import com.epam.training.sportsbeatting.converter.Converter;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.dto.user.create.CreateUserDto;
import com.epam.training.sportsbeatting.dto.user.read.UserDto;
import com.epam.training.sportsbeatting.dto.user.update.UpdateUserDto;
import com.epam.training.sportsbeatting.dto.wager.WagerDto;
import com.epam.training.sportsbeatting.service.UserService;
import com.epam.training.sportsbeatting.service.WagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private WagerService wagerService;

    @Autowired
    private Converter<User, UserDto> userConverter;

    @Autowired
    private Converter<CreateUserDto, User> createUserDtoConverter;

    @Autowired
    private Converter<Wager, WagerDto> wagerConverter;

    @Override
    public UserDto getProfile() {
        return userConverter.convert(userService.getCurrentUser());
    }

    @Override
    public UserDto registerUser(CreateUserDto userDto) {
        User user = createUserDtoConverter.convert(userDto);
        return userConverter.convert(userService.register(user));
    }

    @Override
    public List<WagerDto> getCurrentUserWagers() {
        User user = userService.getCurrentUser();
        return wagerConverter.convertAll(user.getWagers());
    }

    @Override
    public void deleteUnprocessedWager(Long wagerId) {
        User user = userService.getCurrentUser();
        Wager wagerToDelete = user.getWagers().stream()
                .filter(wager -> wager.getId().equals(wagerId))
                .findAny().orElseThrow(() -> new RuntimeException("You can only delete your wagers."));
        wagerService.deleteUnprocessed(wagerToDelete);
    }

    @Override
    public UserDto updateUserDto(UpdateUserDto updateUserDto) {
        User user = userService.getCurrentUser();
        user.setName(updateUserDto.getName());
        user.setAccountNumber(updateUserDto.getAccountNumber());
        user.setBalance(updateUserDto.getBalance());
        updateUserDto.setDateOfBirth(updateUserDto.getDateOfBirth());
        user.setCurrency(updateUserDto.getCurrency());
        return userConverter.convert(userService.save(user));
    }
}
