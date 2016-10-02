package ca.ualberta.cs.habittracker;

import java.util.Date;

/**
 * Created by Master Chief on 2016-09-29.
 */
public class Completion {

    private Date date;

    public Completion (Date d) {
        date = d;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
