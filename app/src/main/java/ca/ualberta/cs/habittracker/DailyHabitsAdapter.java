package ca.ualberta.cs.habittracker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Master Chief on 2016-09-30.
 */
public class DailyHabitsAdapter extends ArrayAdapter<Habit> {

    ArrayList<Habit> habits;
    LayoutInflater inflater;

    public DailyHabitsAdapter(Context context, int resource, ArrayList<Habit> objects, LayoutInflater li) {
        super(context, resource, objects);
        habits = objects;
        inflater = li;
    }

    // code modified from https://code.tutsplus.com/tutorials/android-from-scratch-understanding-adapters-and-adapter-views--cms-26646 Oct 1, 2016
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.main_list, null, false);
        }

        Habit current = habits.get(position);

        TextView habitName = (TextView)convertView.findViewById(R.id.habitName);
        TextView completionCount = (TextView)convertView.findViewById(R.id.completionCount);

        if (current.completedToday())
        {
            habitName.setText(current.getName());
            habitName.setTextColor(Color.GREEN);
        }
        else
        {
            habitName.setText(current.getName());
            habitName.setTextColor(Color.RED);
        }
        completionCount.setText("Total completions: " + Integer.toString(current.getTotalCompletions()));

        return convertView;
    }

}
