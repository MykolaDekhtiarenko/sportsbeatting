package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
