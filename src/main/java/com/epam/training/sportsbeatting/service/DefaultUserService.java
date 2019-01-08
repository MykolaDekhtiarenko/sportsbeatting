package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.Currency;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserRepository;
import com.epam.training.sportsbeatting.service.environment.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private static final String USER_BALANCE_PROPERTY = "app.user.balance.default";
    private static final String USER_CURRENCY_PROPERTY = "app.user.currency";

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserRepository playerRepository;

    @Override
    public User register(User user) {
        user.setBalance(Integer.valueOf(propertyService.getPropertyOrDefault(USER_BALANCE_PROPERTY, "10000")));
        user.setCurrency(Currency.valueOf(propertyService.getPropertyOrDefault(USER_CURRENCY_PROPERTY, "UAN")));
        return playerRepository.save(user);
    }
}
