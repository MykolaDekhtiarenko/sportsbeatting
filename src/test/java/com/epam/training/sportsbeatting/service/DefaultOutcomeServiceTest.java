package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.repository.BetRepository;
import com.epam.training.sportsbeatting.repository.OutcomeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultOutcomeServiceTest {

    private static final Long OUTCOME_ID = 1L;

    @Mock
    private OutcomeRepository outcomeRepository;

    @Mock
    private BetRepository betRepository;

    @InjectMocks
    private DefaultOutcomeService testingInstance;

    @Test
    void shouldThrowExceptionWhileChoosingWinOutcome() {
        when(betRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> testingInstance.chooseWinOutcome(OUTCOME_ID));
    }

    @Test
    void shouldChooseWinOutcome() {
        Bet bet = mock(Bet.class);
        Outcome outcome = mock(Outcome.class);

        when(bet.getOutcomes()).thenReturn(Arrays.asList(outcome));
        when(betRepository.findById(anyLong())).thenReturn(Optional.of(bet));

        Outcome result = testingInstance.chooseWinOutcome(OUTCOME_ID);
        verify(betRepository).findById(anyLong());
    }

    @Test
    void save() {
        Outcome outcome = mock(Outcome.class);

        testingInstance.save(outcome);

        verify(outcomeRepository).save(any());
    }

    @Test
    void findAll() {
        testingInstance.findAll();

        verify(outcomeRepository).findAll();
    }

    @Test
    void get() {
        testingInstance.get(OUTCOME_ID);

        verify(outcomeRepository).findById(any());
    }
}