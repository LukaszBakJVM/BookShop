package com.example.book_rental.Penalty;

import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final Penalty penalty;

    public Scheduler(Penalty penalty) {
        this.penalty = penalty;
    }
}
