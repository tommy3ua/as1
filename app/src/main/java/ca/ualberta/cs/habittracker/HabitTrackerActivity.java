package ca.ualberta.cs.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.MenuItem;

public class HabitTrackerActivity extends Activity {

	private ListView todaysHabits;
    private DailyHabitsAdapter adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		todaysHabits = (ListView) findViewById(R.id.todaysHabits);

        // code modified from http://stackoverflow.com/questions/17851687/how-to-handle-the-click-event-in-listview-in-android Sept.30, 2016
        todaysHabits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habit entry = (Habit)parent.getAdapter().getItem(position);
                entry.complete();
                User.getInstance().saveToFile(getApplicationContext());
                adapter.notifyDataSetChanged();
                // complete habits
               // ListEntry entry= (ListEntry) parent.getAdapter().getItem(position);
            }
        });
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		User.getInstance().loadFromFile(getApplicationContext());
        // edit list item xml to change appearance of list item
		adapter = new DailyHabitsAdapter(this,
				R.layout.completions_list, User.getInstance().getTodaysHabits(), getLayoutInflater());
		todaysHabits.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu m) {
		getMenuInflater().inflate(R.menu.main, m);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                return false;
            case R.id.add:
                startActivity(new Intent(this, AddHabitActivity.class));
                return true;
            case R.id.view:
                startActivity(new Intent(this, ViewHabitsActivity.class));
                return true;
            default:
                return false;
                //return super.onOptionsItemSelected(item);
        }
	}
}