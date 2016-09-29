package ca.ualberta.cs.habittracker;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class ViewHabitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_habits);
    }
}
