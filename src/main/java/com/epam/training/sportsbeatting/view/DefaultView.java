package com.epam.training.sportsbeatting.view;

import com.epam.training.sportsbeatting.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DefaultView implements View {

    @Autowired
    private Scanner scanner;

    @Autowired
    @Qualifier("playerRegistrationAction")
    private Action playerRegistrationAction;

    @Autowired
    @Qualifier("playGameAction")
    private Action playGameAction;

    @Override
    public void show() {
        playerRegistrationAction.execute();
        playGameAction.execute();
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public boolean hasValue() {
        return scanner.hasNext();
    }

    @Override
    public String getValue() {
        return scanner.next();
    }

}
