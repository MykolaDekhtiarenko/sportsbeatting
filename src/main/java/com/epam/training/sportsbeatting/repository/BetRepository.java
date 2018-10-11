package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
