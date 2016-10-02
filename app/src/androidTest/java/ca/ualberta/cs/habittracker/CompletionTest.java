package ca.ualberta.cs.habittracker;

import android.test.ActivityInstrumentationTestCase2;
import java.util.Date;

public class CompletionTest extends ActivityInstrumentationTestCase2 {

    public CompletionTest() {
        super(HabitTrackerActivity.class);
    }

    public void testToString() {
        Date date = new Date();
        Completion completion = new Completion(date);
        assertTrue(completion.toString().equals(date.toString()));
    }
}

