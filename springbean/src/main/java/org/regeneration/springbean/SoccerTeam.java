package org.regeneration.springbean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SoccerTeam implements Team{
    @Override
    public void train() {
        System.out.println("Training soccer team");
    }
}
