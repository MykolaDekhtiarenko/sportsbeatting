package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.repository.OutcomeOddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOutcomeOddService implements OutcomeOddService {

    @Autowired
    private OutcomeOddRepository outcomeOddRepository;

    @Override
    public List<OutcomeOdd> findAll() {
        return outcomeOddRepository.findAll();
    }
}
