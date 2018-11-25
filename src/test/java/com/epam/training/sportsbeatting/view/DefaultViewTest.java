package com.epam.training.sportsbeatting.view;

import com.epam.training.sportsbeatting.action.Action;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultViewTest {

    @Mock
    private Action playerRegistrationAction;

    @Mock
    private Action playGameAction;

    @InjectMocks
    private DefaultView testingInstance;

    @Test
    void show() {
        testingInstance.show();
        verify(playerRegistrationAction).execute();
        verify(playGameAction).execute();
    }

}