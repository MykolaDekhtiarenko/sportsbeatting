package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultPlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private DefaultPlayerService testingInstance;

    @Test
    public void save() {
        Player player = mock(Player.class);

        testingInstance.save(player);

        verify(playerRepository).save(player);
    }

}