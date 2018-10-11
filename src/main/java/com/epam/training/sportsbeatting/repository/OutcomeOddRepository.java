package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeOddRepository extends JpaRepository<OutcomeOdd, Long> {
}
