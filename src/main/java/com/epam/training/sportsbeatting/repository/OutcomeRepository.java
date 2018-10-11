package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
}
