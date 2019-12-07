package org.regeneration.springbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component("soccerCoach")
public class SoccerCoach implements Coach {

//    @Autowired
    private Team soccerTeam;

    @Autowired
    public void setSoccerTeam(@Qualifier("soccerTeam") Team sTeam) {
        this.soccerTeam = sTeam;
    }
    //    @Autowired
//    public SoccerCoach(Team soccerTeam) {
//        this.soccerTeam = soccerTeam;
//    }

    @PostConstruct
    public void init() {
        System.out.println("init SoccerCoach...");
    }

    @Override
    public String performDailyWorkout() {
        soccerTeam.train();
        return "SoccerCoach practice";
    }
}
