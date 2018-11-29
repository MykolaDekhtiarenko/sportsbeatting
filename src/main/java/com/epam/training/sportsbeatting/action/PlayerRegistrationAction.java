package com.epam.training.sportsbeatting.action;

import com.epam.training.sportsbeatting.domain.Currency;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.infrastructure.Session;
import com.epam.training.sportsbeatting.service.PlayerService;
import com.epam.training.sportsbeatting.util.reader.InputReader;
import com.epam.training.sportsbeatting.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.google.common.base.Preconditions.checkArgument;

@Component
public class PlayerRegistrationAction implements Action {
    private static final String GET_USERNAME_MESSAGE = "user.registration.name";
    private static final String USERNAME_VALIDATION = "^[a-zA-Z ]*$";
    private static final String GET_ACCOUNT_NUMBER_MESSAGE = "user.registration.account";
    private static final String ACCOUNT_NUMBER_VALIDATION = "^([0-9]){7}-([0-9]){7}";
    private static final String GET_BALANCE_MESSAGE = "user.registration.money";
    private static final String BALANCE_VALIDATION = "^\\d+";
    private static final String GET_CURRENCY_MESSAGE = "user.registration.currency";
    private static final String CURRENCY_VALIDATION = "UAN|EUR|USD";
    private static final String GET_BIRTH_DATE_MESSAGE = "user.registration.birthDate";
    private static final String BIRTH_DATE_VALIDATION = "^([0-9]){4}-([0-9]){2}-([0-9]){2}";
    private static final String GREET_USER_MESSAGE = "user.greeting";
    private static final String MINOR = "You're not allowed to play because you're under 18.";

    @Autowired
    private PlayerService playerService;

    @Autowired
    private InputReader reader;

    @Autowired
    private View view;

    @Autowired
    private Session session;

    @Autowired
    private Environment env;

    @Override
    public void execute() {
        Player player = new Player();
        player.setName(reader.readValue(env.getProperty(GET_USERNAME_MESSAGE), USERNAME_VALIDATION));
        player.setAccountNumber(
                reader.readValue(env.getProperty(GET_ACCOUNT_NUMBER_MESSAGE), ACCOUNT_NUMBER_VALIDATION));
        player.setBalance(
                Integer.valueOf(reader.readValue(env.getProperty(GET_BALANCE_MESSAGE), BALANCE_VALIDATION)));
        player.setCurrency(
                Currency.valueOf(reader.readValue(env.getProperty(GET_CURRENCY_MESSAGE), CURRENCY_VALIDATION)));
        player.setDateOfBirth(LocalDate.parse(
                reader.readValue(env.getProperty(GET_BIRTH_DATE_MESSAGE), BIRTH_DATE_VALIDATION),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ));
        checkArgument(LocalDate.now().getYear() - player.getDateOfBirth().getYear() >= 18, MINOR);
        session.setCurrentPlayer(player);
        playerService.save(player);
        view.printMessage(String.format(env.getProperty(GREET_USER_MESSAGE), player.getName(),
                player.getBalance(), player.getCurrency().name()));
    }

}
