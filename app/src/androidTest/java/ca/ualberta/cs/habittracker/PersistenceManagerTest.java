package ca.ualberta.cs.habittracker;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

public class PersistenceManagerTest extends ActivityInstrumentationTestCase2 {

    private Instrumentation ins;

    public PersistenceManagerTest() {
        super(HabitTrackerActivity.class);
        ins = getInstrumentation();
    }

    public void testLoadHabits() {

    }

    public void testSaveHabits() {

    }
}
