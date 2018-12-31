package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.repository.OutcomeOddRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultOutcomeOddServiceTest {

    @Mock
    private OutcomeOddRepository outcomeOddRepository;

    @InjectMocks
    private DefaultOutcomeOddService testingInstance;

    @Test
    void findAll() {
        testingInstance.findAll();
        verify(outcomeOddRepository).findAll();
    }
}