package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.user.User;

public interface UserService {
    User getCurrentUser();

    User register(User user);

    User save(User user);
}
