package com.epam.training.sportsbeatting.domain.outcome;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "win", columnDefinition = "SMALLINT(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean win;

    @ManyToOne
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    @OneToMany(mappedBy = "outcome", cascade = CascadeType.ALL)
    private List<OutcomeOdd> outcomeOdds;

}
