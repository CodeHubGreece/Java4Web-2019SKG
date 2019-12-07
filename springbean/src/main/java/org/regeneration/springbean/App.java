package org.regeneration.springbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SportsConfig.class);
        Coach soccerCoach = context.getBean("soccerCoach", Coach.class);
//        Coach baseballCoach = context.getBean("baseballCoach", Coach.class);

        List<Coach> coaches = Arrays.asList(soccerCoach);//, baseballCoach);

        for (Coach coach : coaches) {
            System.out.println("Coach: " + coach.performDailyWorkout());
        }

//        System.out.println("soccerCoach: " + soccerCoach.performDailyWorkout());
//        System.out.println("baseballCoach: " + baseballCoach.performDailyWorkout());

    }
}
