package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository playerRepository;

    @Override
    public User save(User player) {
        return playerRepository.save(player);
    }
}
