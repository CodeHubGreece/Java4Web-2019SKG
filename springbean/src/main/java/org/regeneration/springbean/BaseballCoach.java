package org.regeneration.springbean;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
    @Override
    public String performDailyWorkout() {
        return "Baseball practice";
    }
}
