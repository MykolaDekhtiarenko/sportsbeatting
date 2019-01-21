package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.exeptions.SportBettingGeneralException;
import com.epam.training.sportsbeatting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultWagerService implements WagerService {

    private static final String STARTED_EVENT_DELETION = "You are not able to remove wager which event is started";

    @Autowired
    private WagerRepository wagerRepository;

    @Override
    public void deleteUnprocessed(Wager wagerToDelete) {
        if (wagerToDelete.eventIsStarted()) {
            throw new SportBettingGeneralException(STARTED_EVENT_DELETION);
        }
        wagerRepository.deleteById(wagerToDelete.getId());
    }

}
