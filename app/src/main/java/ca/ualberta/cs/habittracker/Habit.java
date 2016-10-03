package ca.ualberta.cs.habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Habit {

    private Date date;
    private String name;
    private ArrayList<Integer> daysOfOccurence;
    private ArrayList<Completion> completions;
    private int dailyCompletions;
    private int id;
    private int[] lastTimeCompleted;

    public Habit(String n, Date d, ArrayList<Integer> day, int i) {
        name = n;
        date = d;
        daysOfOccurence = day;
        completions = new ArrayList<Completion>();
        dailyCompletions = 0;
        id = i;
        lastTimeCompleted = new int[3];
        lastTimeCompleted[0] = -1;
        lastTimeCompleted[1] = -1;
        lastTimeCompleted[2] = -1;
    }

    public String getName() {
        return name;
    }

    public int getTotalCompletions() {
        return completions.size();
    }

    public void complete() {
        completions.add(new Completion(new Date()));
        dailyCompletions++;
        // code taken from http://stackoverflow.com/questions/5050170/how-do-i-get-a-date-without-time-in-java Oct 2, 2016
        Calendar cal = Calendar.getInstance();
        lastTimeCompleted[0] = cal.get(Calendar.MONTH);
        lastTimeCompleted[1] = cal.get(Calendar.DAY_OF_MONTH);
        lastTimeCompleted[2] = cal.get(Calendar.YEAR);
    }

    public ArrayList<Completion> getCompletions() {
        return completions;
    }

    public void deleteCompletion(Completion c) {
        completions.remove(c);
    }

    public boolean occursOnDay(Integer day) {
        if (daysOfOccurence == null)
        {
            return false;
        }
        if (daysOfOccurence.contains(day)) {
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
        Calendar cal = Calendar.getInstance();
        if (lastTimeCompleted[0] != cal.get(Calendar.MONTH)
                || lastTimeCompleted[1] != cal.get(Calendar.DAY_OF_MONTH)
                || lastTimeCompleted[2] != cal.get(Calendar.YEAR)) {
            dailyCompletions = 0;
            return false;
        }
        return dailyCompletions > 0;
    }
}
