package com.epam.training.sportsbeatting.domain.wager;

import com.epam.training.sportsbeatting.domain.Currency;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.user.Player;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
public class Wager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "processed", columnDefinition = "SMALLINT(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean processed;

    @Column(name = "win", columnDefinition = "SMALLINT(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean win;

    @ManyToOne
    @JoinColumn(name = "outcome_odd_id", nullable = false)
    private OutcomeOdd outcomeOdd;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

}
