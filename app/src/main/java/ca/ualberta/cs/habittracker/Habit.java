package ca.ualberta.cs.habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by L on 9/27/2016.
 */
public class Habit {

    public enum Days {Mon, Tue, Wed, Thurs, Fri, Sat, Sun};

    private String dateCreated;
    private String name;
    private ArrayList<Days> days;
    private int totalCompletions;
    private int dailyCompletions;

    public Habit(String n, ArrayList<Days> d) {
        name = n;
        days = d;
        totalCompletions = 0;
        dailyCompletions = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalCompletions() {
        return totalCompletions;
    }

    public int getDailyCompletions() {
        return dailyCompletions;
    }

    public void editName(String n) {
        name = n;
    }

    public void complete() {
        totalCompletions++;
        dailyCompletions++;
    }

    public void resetCompletions() {
        totalCompletions = 0;
        dailyCompletions = 0;
    }

    public boolean occursOnDay(Habit.Days day) {
        if (days.contains(day)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean completedToday() {
        return dailyCompletions > 0;
    }
}
