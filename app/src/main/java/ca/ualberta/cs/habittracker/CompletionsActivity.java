package ca.ualberta.cs.habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CompletionsActivity extends Activity {

    private int habitID;
    private Completion selectedCompletion;
    private ListView completions;
    private Button deleteCompletion;
    private Button back;
    private ArrayAdapter<Completion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completions);
        completions = (ListView) findViewById(R.id.completionsList);
        deleteCompletion = (Button) findViewById(R.id.deleteCompletion);
        back = (Button) findViewById(R.id.back);

        completions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCompletion = (Completion) completions.getItemAtPosition(position);
            }
        });

        deleteCompletion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (selectedCompletion != null) {
                    User.getInstance().getHabitByID(habitID).deleteCompletion(selectedCompletion);
                }
                User.getInstance().saveToFile(getApplicationContext());
                adapter.notifyDataSetChanged();
                setResult(RESULT_OK);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        habitID = getIntent().getIntExtra("id", -1);

        adapter = new ArrayAdapter<Completion>(this,
                R.layout.completions_list, User.getInstance().getHabitByID(habitID).getCompletions());
        completions.setAdapter(adapter);
    }
}
