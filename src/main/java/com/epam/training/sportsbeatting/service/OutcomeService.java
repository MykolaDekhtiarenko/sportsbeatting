package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;

import java.util.List;
import java.util.Optional;

public interface OutcomeService {
    Outcome save(Outcome outcome);
    List<Outcome> findAll();
    Optional<Outcome> get(Long id);
    Outcome chooseWinOutcome(Long betId);
}
