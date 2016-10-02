package ca.ualberta.cs.habittracker;

import java.util.Date;

public class Completion {

    private Date date;

    public Completion (Date d) {
        date = d;
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
