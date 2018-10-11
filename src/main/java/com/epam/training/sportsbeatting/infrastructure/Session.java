package com.epam.training.sportsbeatting.infrastructure;

import com.epam.training.sportsbeatting.domain.user.Player;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Session {
    private Player currentPlayer;
}
