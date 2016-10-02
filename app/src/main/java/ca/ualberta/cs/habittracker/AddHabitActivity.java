package ca.ualberta.cs.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class AddHabitActivity extends Activity {

    private DatePicker datePicker;
    private Button cancel;
    private Button save;
    private EditText name;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        cancel = (Button)findViewById(R.id.cancelButton);
        save = (Button)findViewById(R.id.saveButton);
        name = (EditText)findViewById(R.id.enterName);
        monday = (CheckBox)findViewById(R.id.monday);
        tuesday = (CheckBox)findViewById(R.id.tuesday);
        wednesday = (CheckBox)findViewById(R.id.wednesday);
        thursday = (CheckBox)findViewById(R.id.thursday);
        friday = (CheckBox)findViewById(R.id.friday);
        saturday = (CheckBox)findViewById(R.id.saturday);
        sunday = (CheckBox)findViewById(R.id.sunday);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = name.getText().toString();
                ArrayList<Integer> days = new ArrayList<Integer>();
                if (monday.isChecked()) { days.add(Calendar.MONDAY); }
                if (tuesday.isChecked()) { days.add(Calendar.TUESDAY); }
                if (wednesday.isChecked()) { days.add(Calendar.WEDNESDAY); }
                if (thursday.isChecked()) { days.add(Calendar.THURSDAY); }
                if (friday.isChecked()) { days.add(Calendar.FRIDAY); }
                if (saturday.isChecked()) { days.add(Calendar.SATURDAY); }
                if (sunday.isChecked()) { days.add(Calendar.SUNDAY); }
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year =  datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                User.getInstance().addHabit(new Habit(text, calendar.getTime(), days, User.getInstance().generateHabitID()));
                User.getInstance().saveToFile(getApplicationContext());
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

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
        User.getInstance().loadFromFile(getApplicationContext());
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.main, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                startActivity(new Intent(this, HabitTrackerActivity.class));
                return false;
            case R.id.add:
                return false;
            case R.id.view:
                startActivity(new Intent(this, ViewHabitsActivity.class));
                return true;
            default:
                return false;
            //return super.onOptionsItemSelected(item);
        }
    }
    */
}
