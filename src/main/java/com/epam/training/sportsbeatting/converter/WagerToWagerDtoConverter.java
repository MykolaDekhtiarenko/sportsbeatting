package com.epam.training.sportsbeatting.converter;

import com.epam.training.sportsbeatting.domain.sportevent.FootballSportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.TennisSportEvent;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.dto.wager.WagerDto;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class WagerToWagerDtoConverter implements Converter<Wager, WagerDto> {

    private Map<Class, String> typesRepresentation = ImmutableMap.of(
            FootballSportEvent.class, "Football",
            TennisSportEvent.class, "Tennis"
    );

    @Override
    public WagerDto convert(Wager wager) {
        SportEvent event = wager.getOutcomeOdd().getOutcome().getBet().getSportEvent();
        return WagerDto.builder()
                .wagerId(wager.getId())
                .eventTitle(event.getTitle())
                .eventType(typesRepresentation.get(event.getClass()))
                .betType(wager.getOutcomeOdd().getOutcome().getBet().getType().name())
                .outcomeOdd(wager.getOutcomeOdd().getValue().toString())
                .outcomeValue(wager.getOutcomeOdd().getOutcome().getValue())
                .wagerAmount(String.valueOf(wager.getAmount()))
                .wagerCurrency(String.valueOf(wager.getCurrency()))
                .win(convertBooleanToYesNo(wager.getWin()))
                .processed(convertBooleanToYesNo(wager.isProcessed()))
                .eventIsStarted(wager.eventIsStarted())
                .build();
    }

    private String convertBooleanToYesNo(Boolean b) {
        if (Objects.isNull(b)) {
            return "-";
        } else if (b) {
            return "Yes";
        } else {
            return "No";
        }
    }

}
