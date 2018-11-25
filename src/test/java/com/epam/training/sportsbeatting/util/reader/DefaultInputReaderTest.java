package com.epam.training.sportsbeatting.util.reader;

import com.epam.training.sportsbeatting.view.View;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultInputReaderTest {

    private static final String MESSAGE = "";
    private static final String REGEX = "[0-9]";
    private static final String ERROR_MESSAGE = "";

    private static final String NON_MATCHING_REGEX_VALUE = "aa";
    private static final String MATCHING_REGEX_VALUE = "1";
    private static final String INBOUNDS_VALUE = "100";
    private static final String OUTBOUNDS_VALUE = "1";
    private static final String ANY_STRING = "simple string";

    @Mock
    private View view;

    @InjectMocks
    private DefaultInputReader testingInstance;

    @Test
    void shouldPrintErrorMessageWhenMessageDoesNotMatch() {
        when(view.getValue()).thenReturn(NON_MATCHING_REGEX_VALUE, MATCHING_REGEX_VALUE);
        when(view.hasValue()).thenReturn(true);
        doNothing().when(view).printMessage(anyString());

        String res = testingInstance.readValue(MESSAGE, REGEX, ERROR_MESSAGE);

        verify(view).printErrorMessage(ERROR_MESSAGE);
        Assertions.assertThat(res).matches(MATCHING_REGEX_VALUE);

    }

    @Test
    void shouldPrintMessageWhenMessageDoesNotMatch() {
        when(view.getValue()).thenReturn(NON_MATCHING_REGEX_VALUE, MATCHING_REGEX_VALUE);
        when(view.hasValue()).thenReturn(true);
        doNothing().when(view).printMessage(anyString());

        String res = testingInstance.readValue(MESSAGE, REGEX);

        verify(view).printErrorMessage(MESSAGE);
        Assertions.assertThat(res).matches(MATCHING_REGEX_VALUE);
    }

    @Test
    void shouldGetValue() {
        when(view.getValue()).thenReturn(ANY_STRING);

        String res = testingInstance.readValue(MESSAGE);

        Assertions.assertThat(res).matches(ANY_STRING);

    }

    @Test
    void shouldVaildateBoundsAndPrintErrorMessage() {
        when(view.getValue()).thenReturn(OUTBOUNDS_VALUE, INBOUNDS_VALUE);
        when(view.hasValue()).thenReturn(true);
        doNothing().when(view).printMessage(anyString());

        Integer res = testingInstance.readValue(MESSAGE, 11, 101);

        verify(view).printErrorMessage(anyString());
        Assertions.assertThat(res).isEqualTo(Integer.valueOf(INBOUNDS_VALUE));
    }
}