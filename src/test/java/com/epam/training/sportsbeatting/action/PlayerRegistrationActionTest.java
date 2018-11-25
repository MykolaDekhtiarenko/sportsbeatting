package com.epam.training.sportsbeatting.action;

import com.epam.training.sportsbeatting.domain.Currency;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.infrastructure.Session;
import com.epam.training.sportsbeatting.service.PlayerService;
import com.epam.training.sportsbeatting.util.reader.InputReader;
import com.epam.training.sportsbeatting.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.core.env.Environment;

import java.util.Arrays;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerRegistrationActionTest {
    private static final String GET_USERNAME_MESSAGE = "user.registration.name";
    private static final String GET_ACCOUNT_NUMBER_MESSAGE = "user.registration.account";
    private static final String GET_BALANCE_MESSAGE = "user.registration.money";
    private static final String GET_CURRENCY_MESSAGE = "user.registration.currency";
    private static final String GET_BIRTH_DATE_MESSAGE = "user.registration.birthDate";
    private static final String GREET_USER_MESSAGE = "user.greeting";

    private static final String BALANCE = "100000";
    private static final String BIRTH_DATE = "1997-01-01";
    private static final String EMPTY = "";

    @Mock
    private PlayerService playerService;

    @Mock
    private InputReader reader;

    @Mock
    private View view;

    @Mock
    private Session session;

    @Mock
    private Environment env;

    @InjectMocks
    private PlayerRegistrationAction playerRegistrationAction;

    @BeforeEach
    public void setUp() {
        doNothing().when(view).printMessage(anyString());
        when(env.getProperty(anyString())).thenReturn(EMPTY);
        when(reader.readValue(anyString(), anyString())).thenReturn(EMPTY, EMPTY, BALANCE, Currency.USD.toString(), BIRTH_DATE);
    }

    @Test
    public void shouldAddRegisteredPlayerToSession() {
        playerRegistrationAction.execute();
        verify(session).setCurrentPlayer(any());

    }

    @Test
    public void shouldSaveRegisteredPlayer() {
        playerRegistrationAction.execute();
        verify(playerService).save(any());
    }

}