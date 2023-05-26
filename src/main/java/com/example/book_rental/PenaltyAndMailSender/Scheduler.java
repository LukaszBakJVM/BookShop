package com.example.book_rental.PenaltyAndMailSender;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final Penalty penalty;

    public Scheduler(Penalty penalty) {
        this.penalty = penalty;
    }

        @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void setPenalty(){
        penalty.findBookOver30days();

    }


}
