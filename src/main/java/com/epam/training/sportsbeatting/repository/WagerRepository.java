package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.wager.Wager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagerRepository extends JpaRepository<Wager, Long> {
}
