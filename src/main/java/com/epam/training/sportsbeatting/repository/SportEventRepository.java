package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
}
