package com.epam.training.sportsbeatting.dto.wager;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WagerDto {
    private long wagerId;
    private String eventTitle;
    private String eventType;
    private String betType;
    private String outcomeValue;
    private String outcomeOdd;
    private String wagerAmount;
    private String wagerCurrency;
    private String win;
    private String processed;
    private boolean eventIsStarted;
}
