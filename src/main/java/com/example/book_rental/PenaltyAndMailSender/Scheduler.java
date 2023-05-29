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
    //Every day at 1am
       @Scheduled(cron = "0 0 1 * * ?")
    //@Scheduled(cron = "0 * * ? * *")
    public void setPenalty() {
            penalty.findBookOver30days();
        }
        //Every day at 2am
        @Scheduled (cron = "0 0 2 * * ?" )

    //@Scheduled(cron = "0 * * ? * *")
        public void sendMail(){
             sendMail.sendMail();


    }


}
