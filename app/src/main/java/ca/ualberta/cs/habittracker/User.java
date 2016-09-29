package ca.ualberta.cs.habittracker;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by L on 9/27/2016.
 */
public class User {

    private ArrayList<Habit> habits;
    private PersistenceManager pm;
    private Habit.Days today;

    public User() {
        habits = new ArrayList<Habit>();
        pm = new PersistenceManager();
    }

    public void LoadFromFile() {
        habits = pm.LoadHabits();
    }

    public void SaveToFile() {
        pm.SaveHabits(habits);
    }

    public ArrayList<Habit> GetHabits() {
        return habits;
    }

    public ArrayList<Habit> GetTodaysHabits() {
        ArrayList<Habit> result = new ArrayList<Habit>();
        for (int i = 0; i < result.size(); i++) {
            if (habits.get(i).occursOnDay(today)) {
                result.add(habits.get(i));
            }
        }
        return result;
    }

    public void AddHabit(Habit h) {
        habits.add(h);
    }

    public void DeleteHabit(Habit h) {
        if (!habits.remove(h)) {
            Log.d("habit", "delete habit failed");
        }
    }
}