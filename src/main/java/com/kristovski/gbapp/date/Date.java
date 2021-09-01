package com.kristovski.gbapp.date;

public class Date {

    private Date date;

    public Date() {
    }

    public Date(Date dateString) {
        this.date = dateString;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
