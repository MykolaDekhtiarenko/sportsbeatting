package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.Currency;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.exeptions.UserWithSuchEmailExistsException;
import com.epam.training.sportsbeatting.repository.UserRepository;
import com.epam.training.sportsbeatting.service.environment.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private static final String USER_BALANCE_PROPERTY = "app.user.balance.default";
    private static final String USER_CURRENCY_PROPERTY = "app.user.currency";

    @Value("${user.validation.email.exists}")
    private String userWithSuchEmailExistsMessage;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.getOne(principal.getId());
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserWithSuchEmailExistsException(userWithSuchEmailExistsMessage);
        }
        user.setBalance(Integer.valueOf(propertyService.getPropertyOrDefault(USER_BALANCE_PROPERTY, "10000")));
        user.setCurrency(Currency.valueOf(propertyService.getPropertyOrDefault(USER_CURRENCY_PROPERTY, "UAN")));
        return userRepository.save(user);
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }
}
