package com.epam.training.sportsbeatting.domain.user;

import com.epam.training.sportsbeatting.domain.wager.Wager;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity(name = "User")
public class Player extends User {

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Wager> wagers;

}
