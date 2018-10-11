package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.repository.BetRepository;
import com.epam.training.sportsbeatting.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DefaultOutcomeService implements OutcomeService {
    private static final String BET_DOES_NOT_EXISTS = "Bet with id: %s doesn't exist!";

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private BetRepository betRepository;

    @Override
    @Transactional
    public Outcome chooseWinOutcome(Long id) {
        List<Outcome> outcomes = betRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(BET_DOES_NOT_EXISTS, id)))
                .getOutcomes();
        return outcomes.get(new Random().nextInt(outcomes.size()));
    }

    @Override
    public Outcome save(Outcome outcome) {
        return outcomeRepository.save(outcome);
    }

    @Override
    public List<Outcome> findAll() {
        return outcomeRepository.findAll();
    }

    @Override
    public Optional<Outcome> get(Long id) {
        return outcomeRepository.findById(id);
    }
}
