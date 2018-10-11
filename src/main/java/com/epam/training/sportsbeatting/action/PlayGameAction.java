package com.epam.training.sportsbeatting.action;

import com.epam.training.sportsbeatting.util.reader.InputReader;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.infrastructure.Session;
import com.epam.training.sportsbeatting.service.OutcomeOddService;
import com.epam.training.sportsbeatting.service.OutcomeService;
import com.epam.training.sportsbeatting.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class PlayGameAction implements Action {
    private static final String OUTCOME_MESSAGE = "game.outcome";
    private static final String CONTINUE_MESSAGE = "game.chooseToContinue";
    private static final String NEW_BALANCE_MESSAGE = "user.balance";

    @Autowired
    private View view;

    @Autowired
    private Environment env;

    @Autowired
    private Session session;

    @Autowired
    private InputReader reader;

    @Autowired
    private OutcomeOddService outcomeOddService;

    @Autowired
    private OutcomeService outcomeService;

    @Override
    public void execute() {
        String choice;
        do {
            showNewBalance(processWager(bet()));
            choice = reader.readValue(env.getProperty(CONTINUE_MESSAGE));
        }
        while (!choice.equals("q"));
    }

    private Wager bet() {
        List<OutcomeOdd> outcomeOdds = outcomeOddService.findAll();
        IntStream.range(1, outcomeOdds.size() + 1).forEach(i -> showOutcomeOddToBetOn(i, outcomeOdds.get(i - 1)));
        int choice = reader.readValue("Your choice: ", 0, outcomeOdds.size());
        Player current = session.getCurrentPlayer();
        return Wager.builder()
                .amount(reader.readValue("Amount: ", 0, current.getBalance()))
                .currency(session.getCurrentPlayer().getCurrency())
                .timestamp(LocalDateTime.now())
                .outcomeOdd(outcomeOdds.get(choice))
                .build();
    }

    private Player processWager(Wager wager) {
        Player current = session.getCurrentPlayer();
        Outcome winningOutcome = outcomeService.chooseWinOutcome(wager.getOutcomeOdd().getOutcome().getBet().getId());
        if (winningOutcome == wager.getOutcomeOdd().getOutcome()) {
            current.setBalance((int) (current.getBalance() + wager.getAmount() * wager.getOutcomeOdd().getValue()));
        } else {
            current.setBalance(current.getBalance() - wager.getAmount());
        }
        return current;
    }

    private void showOutcomeOddToBetOn(int index, OutcomeOdd odd) {
        view.printMessage(String.format(env.getProperty(OUTCOME_MESSAGE), index,
                odd.getOutcome().getBet().getSportEvent().getTitle(), odd.getOutcome().getValue(), odd.getValue(),
                odd.getValidFrom(), odd.getValidTo()));
    }

    private void showNewBalance(Player player) {
        view.printMessage(String.format(env.getProperty(NEW_BALANCE_MESSAGE), player.getBalance()));
    }

}
