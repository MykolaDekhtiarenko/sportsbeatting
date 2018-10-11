package com.epam.training.sportsbeatting.view;

public interface View {

    void show();

    void printMessage(String message);

    void printErrorMessage(String message);

    boolean hasValue();

    String getValue();

}
