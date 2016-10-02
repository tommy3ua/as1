package ca.ualberta.cs.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ViewHabitsActivity extends Activity {

    private ListView habits;
    private ViewHabitsAdapter adapter;
    private Button delete;
    private Button completions;
    private Habit selectedHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_habits);
        delete = (Button)findViewById(R.id.delete);
        completions = (Button)findViewById(R.id.completions);
        habits = (ListView) findViewById(R.id.allHabits);

        // code modified from http://stackoverflow.com/questions/4508979/android-listview-get-selected-item Oct.1, 2016
        habits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedHabit = (Habit)habits.getItemAtPosition(position);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (selectedHabit != null) { User.getInstance().deleteHabit(selectedHabit); }
                User.getInstance().saveToFile(getApplicationContext());
                adapter.notifyDataSetChanged();
                setResult(RESULT_OK);
            }
        });

        completions.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                if (selectedHabit != null) {
                    Intent intent = new Intent(getApplicationContext(), CompletionsActivity.class);
                    intent.putExtra("id", selectedHabit.getID());
                    startActivity(intent);
                }
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
        adapter = new ViewHabitsAdapter(this,
                R.layout.completions_list, User.getInstance().getHabits(), getLayoutInflater());
        habits.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.main, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                finish();
                return false;
            case R.id.add:
                startActivity(new Intent(this, AddHabitActivity.class));
                return true;
            case R.id.view:
                return false;
            default:
                return false;
            //return super.onOptionsItemSelected(item);
        }
    }
}
