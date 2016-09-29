package ca.ualberta.cs.habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HabitTrackerActivity extends Activity {

    private User user;

	private ListView todaysHabits;
    private Button allHabits;
    private Button addHabit;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        user = new User();
		allHabits = (Button) findViewById(R.id.allHabits);
        addHabit = (Button) findViewById(R.id.addHabit);
		todaysHabits = (ListView) findViewById(R.id.todaysHabits);

		allHabits.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
                // change activity
				finish();
			}
		});

        addHabit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                // change activity
                finish();
            }
        });
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
        user = new User();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}
}