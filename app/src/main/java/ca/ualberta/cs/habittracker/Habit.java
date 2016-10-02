package ca.ualberta.cs.habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by L on 9/27/2016.
 */
public class Habit {

    private Date date;
    private String name;
    private ArrayList<Integer> days;
    private ArrayList<Completion> completions;
    private int dailyCompletions;
    private int id;

    public Habit(String n, Date d, ArrayList<Integer> day, int i) {
        name = n;
        date = d;
        days = day;
        completions = new ArrayList<Completion>();
        dailyCompletions = 0;
        id = i;
    }

    @Override
    public String toString() {
        if (completedToday()) {
            return name + " - COMPLETE";
        }
        else {
            return name + " - INCOMPLETE";
        }
    }

    public String getName() {
        return name;
    }

    public Date getDate() { return date; }

    public int getTotalCompletions() {
        return completions.size();
    }

    public int getDailyCompletions() {
        return dailyCompletions;
    }

    public void editName(String n) {
        name = n;
    }

    public void editDate(Date d) { date = d; }

    public void complete() {
        completions.add(new Completion(new Date()));
        dailyCompletions++;
    }

    public ArrayList<Completion> getCompletions() {
        return completions;
    }

    public void deleteCompletion(Completion c) {
        completions.remove(c);
    }

    public void resetCompletions() {
        dailyCompletions = 0;
    }

    public boolean occursOnDay(Integer day) {
        if (days.contains(day)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getID() {
        return id;
    }

    public boolean completedToday() {
        return dailyCompletions > 0;
    }
}
