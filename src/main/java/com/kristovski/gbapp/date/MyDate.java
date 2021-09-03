package com.kristovski.gbapp.date;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MyDate {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public MyDate() {
    }

    public MyDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
