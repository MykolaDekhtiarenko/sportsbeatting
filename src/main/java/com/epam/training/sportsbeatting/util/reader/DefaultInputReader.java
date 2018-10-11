package com.epam.training.sportsbeatting.util.reader;

import com.epam.training.sportsbeatting.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultInputReader implements InputReader {
    private static final String NOT_VALID_NUMBER_MESSAGE = "Should be valid number between %d and %d.";

    @Autowired
    private View view;

    public String readValue(String message, String regexp) {
        String res;
        view.printMessage(message);
        while (!(view.hasValue() && (res = view.getValue()).matches(regexp))) {
            view.printErrorMessage(message);
        }
        return res;
    }

    public String readValue(String message, String regexp, String errorMessage) {
        String res;
        view.printMessage(message);
        while (!(view.hasValue() && (res = view.getValue()).matches(regexp))) {
            view.printErrorMessage(errorMessage + "\n" + message);
        }
        return res;
    }

    @Override
    public String readValue(String message) {
        view.printMessage(message);
        return view.getValue();
    }

    @Override
    public int readValue(String message, int moreThen, int lessThen) {
        String res;
        view.printMessage(message);
        while (!(view.hasValue() && (res = view.getValue()).matches("\\d+") && Integer.valueOf(res) > moreThen
                && Integer.valueOf(res) < lessThen)) {
            view.printErrorMessage(String.format(NOT_VALID_NUMBER_MESSAGE, moreThen, lessThen));
        }
        return Integer.valueOf(res);
    }
}