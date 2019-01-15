package com.epam.training.sportsbeatting.domain.bet;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BetType type;

    @ManyToOne
    @JoinColumn(name = "sport_event_id", nullable = false)
    private SportEvent sportEvent;

    @ToString.Exclude
    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL)
    private List<Outcome> outcomes;
}
