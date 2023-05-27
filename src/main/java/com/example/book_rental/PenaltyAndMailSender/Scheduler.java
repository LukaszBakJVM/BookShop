package com.example.book_rental.PenaltyAndMailSender;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final Penalty penalty;
    private final SendMail sendMail;

    public Scheduler(Penalty penalty, SendMail sendMail) {
        this.penalty = penalty;
        this.sendMail = sendMail;

    }

        @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void setPenalty() {
            penalty.findBookOver30days();
        }
        @Scheduled (cron = "0 0/1 * 1/1 * ?" )
        public void sendMail(){
             sendMail.sendMail();


    }


}
