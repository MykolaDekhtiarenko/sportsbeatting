package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.wager.Wager;

public interface WagerService {
    void deleteUnprocessed(Wager wagerToDelete);
}
