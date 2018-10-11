package com.epam.training.sportsbeatting.util.reader;

public interface InputReader {
    String readValue(String message, String regexp);

    String readValue(String message, String regexp, String errorMessage);

    String readValue(String message);

    int readValue(String message, int moreThen, int lessThen);
}
