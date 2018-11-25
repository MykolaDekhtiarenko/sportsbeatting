package com.epam.training.sportsbeatting.action;

import com.epam.training.sportsbeatting.domain.Currency;
import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.infrastructure.Session;
import com.epam.training.sportsbeatting.service.OutcomeOddService;
import com.epam.training.sportsbeatting.service.OutcomeService;
import com.epam.training.sportsbeatting.util.reader.InputReader;
import com.epam.training.sportsbeatting.view.View;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.env.Environment;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PlayGameActionTest {
    private static final String OUTCOME_VALUE = "outcome value";
    private static final String SPORT_EVENT_TITLE = "title";
    private static final Long BET_ID = 1L;
    private static final int OUTCOME_CHOICE = 1;
    private static final int BET_AMOUNT = 50000;

    @Mock
    private View view;
    @Mock
    private Environment env;
    @Mock
    private Session session;
    @Mock
    private InputReader reader;
    @Mock
    private OutcomeOddService outcomeOddService;
    @Mock
    private OutcomeService outcomeService;
    @InjectMocks
    private PlayGameAction playGameAction;

    @Mock
    private Outcome winOutcome;

    @BeforeEach
    public void setUp() {
        OutcomeOdd outcomeOdd = getMockedOutcomeOdd(winOutcome, 2.0);
        Bet bet = getMockedBet();

        when(reader.readValue(anyString())).thenReturn("q");
        when(reader.readValue(anyString(), anyInt(), anyInt())).thenReturn(OUTCOME_CHOICE, BET_AMOUNT);

        when(outcomeOddService.findAll()).thenReturn(Arrays.asList(outcomeOdd));
        when(winOutcome.getBet()).thenReturn(bet);
        when(winOutcome.getValue()).thenReturn(OUTCOME_VALUE);
        when(env.getProperty(anyString())).thenReturn(Strings.EMPTY);

        doNothing().when(view).printMessage(anyString());
    }

    @Test
    public void shouldWin() {
        Player player = getMockedPlayer(100000, Currency.UAN);

        when(outcomeService.chooseWinOutcome(anyLong())).thenReturn(winOutcome);
        when(session.getCurrentPlayer()).thenReturn(player);

        playGameAction.execute();

        Assertions.assertThat(player.getBalance()).isEqualTo(200000);
    }

    @Test
    public void shouldLose() {
        Player player = getMockedPlayer(100000, Currency.UAN);

        when(outcomeService.chooseWinOutcome(anyLong())).thenReturn(mock(Outcome.class));
        when(session.getCurrentPlayer()).thenReturn(player);

        playGameAction.execute();

        Assertions.assertThat(player.getBalance()).isEqualTo(50000);
    }


    private OutcomeOdd getMockedOutcomeOdd(Outcome outcome, Double value) {
        OutcomeOdd outcomeOdd = mock(OutcomeOdd.class);
        when(outcomeOdd.getOutcome()).thenReturn(outcome);
        when(outcomeOdd.getValue()).thenReturn(value);
        return outcomeOdd;
    }

    private Player getMockedPlayer(int balance, Currency currency) {
        Player player = spy(Player.class);
        when(player.getCurrency()).thenReturn(currency);
        doCallRealMethod().when(player).getBalance();
        doCallRealMethod().when(player).setBalance(anyInt());
        player.setBalance(balance);
        return player;
    }

    private Bet getMockedBet() {
        Bet bet = mock(Bet.class);
        SportEvent event = getMockedSportEvent(SPORT_EVENT_TITLE);
        when(bet.getSportEvent()).thenReturn(event);
        return bet;
    }

    private SportEvent getMockedSportEvent(String sportEventTitle) {
        SportEvent sportEvent = mock(SportEvent.class);
        return sportEvent;
    }


}