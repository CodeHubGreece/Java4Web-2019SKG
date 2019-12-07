package org.regeneration.springbean;

import org.springframework.stereotype.Component;

@Component
public class BaseballTeam implements Team {
    @Override
    public void train() {
        System.out.println("Training baseball team");

    }
}
