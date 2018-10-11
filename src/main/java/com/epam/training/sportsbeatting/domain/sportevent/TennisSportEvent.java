package com.epam.training.sportsbeatting.domain.sportevent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TennisSportEvent")
@DiscriminatorValue("TennisSportEvent")
public class TennisSportEvent extends SportEvent{
}
