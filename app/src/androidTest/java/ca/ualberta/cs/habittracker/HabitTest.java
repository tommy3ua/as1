package ca.ualberta.cs.habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Master Chief on 2016-09-29.
 */
public class HabitTest extends ActivityInstrumentationTestCase2 {

    public HabitTest() {
        super(HabitTrackerActivity.class);
    }

    public void testGetName() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 0);
        assertTrue(habit.getName().equals("name"));
    }

    public void testComplete() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 0);
        habit.complete();
        assertTrue(habit.getCompletions().size() > 0);
    }

    public void testGetTotalCompletions() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 0);
        habit.complete();
        habit.complete();
        assertTrue(habit.getTotalCompletions() == 2);
    }

    public void testGetCompletions() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 0);
        habit.complete();
        habit.complete();
        assertTrue(habit.getCompletions().size() == 2);
    }

    public void testDeleteCompletion() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 0);
        habit.complete();
        habit.complete();
        habit.deleteCompletion(habit.getCompletions().get(1));
        assertTrue(habit.getCompletions().size() == 1);
    }

    public void testOccursOnDay() {
        ArrayList<Integer> ints = new ArrayList<Integer>();
        ints.add(Calendar.THURSDAY);
        Habit habit = new Habit("name", new Date(), ints, 0);
        assertTrue(habit.occursOnDay(Calendar.THURSDAY));
        assertFalse(habit.occursOnDay(Calendar.FRIDAY));
    }

    public void testGetID() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 5);
        assertTrue(habit.getID() == 5);
    }

    public void testCompletedToday() {
        Habit habit = new Habit("name", new Date(), new ArrayList<Integer>(), 0);
        habit.complete();
        assertTrue(habit.completedToday());
    }
}
