package ca.ualberta.cs.habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tommy3 on 10/3/16.
 */
public class UserTest extends ActivityInstrumentationTestCase2 {

    public UserTest() {
        super(HabitTrackerActivity.class);
    }

    // note that throughout the test, User is static so it preserves habit list between tests

    public void testAddHabit() {
        ArrayList<Integer> day = new ArrayList<Integer>();
        day.add(Calendar.FRIDAY);
        User.getInstance().addHabit("test", new Date(), day);
        assertTrue(User.getInstance().getHabits().size() == 1);
    }

    public void testDeleteHabit() {
        ArrayList<Integer> day = new ArrayList<Integer>();
        day.add(Calendar.FRIDAY);
        Habit h = new Habit("test", new Date(), day, 1);
        User.getInstance().getHabits().add(h);
        User.getInstance().deleteHabit(h);
        assertTrue(User.getInstance().getHabits().size() == 1);
    }

    public void testGetHabitByID() {
        ArrayList<Integer> day = new ArrayList<Integer>();
        day.add(Calendar.FRIDAY);
        Habit h = new Habit("test", new Date(), day, 11);
        User.getInstance().getHabits().add(h);
        assertTrue(User.getInstance().getHabitByID(11) == h);
    }

    public void testGetHabits() {
        ArrayList<Integer> day = new ArrayList<Integer>();
        day.add(Calendar.FRIDAY);
        User.getInstance().addHabit("test", new Date(), day);
        User.getInstance().addHabit("test", new Date(), day);
        assertTrue(User.getInstance().getHabits().size() == 4);
    }

    public void testGetTodaysHabits() {
        ArrayList<Integer> day = new ArrayList<Integer>();
        day.add(Calendar.FRIDAY);
        User.getInstance().addHabit("test", new Date(), day);
        assertTrue(User.getInstance().getTodaysHabits().size() == 0);
        ArrayList<Integer> day2 = new ArrayList<Integer>();
        day.add(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        User.getInstance().addHabit("test", new Date(), day2);
        assertTrue(User.getInstance().getTodaysHabits().size() == 1);
    }
}