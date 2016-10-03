package ca.ualberta.cs.habittracker;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// acts as a controller; i.e. activities interact with the core only through the User class

public class User {

    private ArrayList<Habit> habits;
    private static PersistenceManager pm;
    private static Integer today;

    private static User ourInstance = null;

    // static class so the API can interact with the main code consistently through the User class
    public static User getInstance() {
        if (ourInstance == null) {
            ourInstance = new User();
            pm = new PersistenceManager();
            today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            return ourInstance;
        }
        else {
            if (today != Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
            {
                today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            }
            return ourInstance;
        }
    }

    private User() {
    }

    public void loadFromFile(Context c) {
        habits = pm.loadHabits(c);
    }

    public void saveToFile(Context c) {
        pm.saveHabits(habits, c);
    }

    public ArrayList<Habit> getHabits() {
        return habits;
    }

    public ArrayList<Habit> getTodaysHabits() {
        ArrayList<Habit> result = new ArrayList<Habit>();
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).occursOnDay(today)) {
                result.add(habits.get(i));
            }
        }
        return result;
    }

    public void addHabit(String t, Date d, ArrayList<Integer> e) {
        habits.add(new Habit(t, d, e, generateHabitID()));
    }

    public void deleteHabit(Habit h) {
        habits.remove(h);
    }

    public Habit getHabitByID(int id) {
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).getID() == id)
            {
                return habits.get(i);
            }
        }
        return null;
    }

    // when the user wants to add a new habit, they should generate a unique ID for each habit
    private int generateHabitID() {
        Boolean success;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            success = true;
            for (int j = 0; j < habits.size(); j++) {
                if (i == habits.get(j).getID()) {
                    success = false;
                    break;
                }
            }
            if (success)
            {
                return i;
            }
        }
        return -1;
    }
}