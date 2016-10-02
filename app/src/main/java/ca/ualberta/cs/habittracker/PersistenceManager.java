package ca.ualberta.cs.habittracker;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PersistenceManager {

    private static final String FILENAME = "tommy3habittracker.sav";

    public PersistenceManager() {

    }

    public ArrayList<Habit> loadHabits(Context context) {
        ArrayList<Habit> result = new ArrayList<Habit>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22, 2016
            Type listType = new TypeToken<ArrayList<Habit>>() {
            }.getType();
            result = gson.fromJson(in, listType);
            in.close();
            fis.close();
        } catch (JsonSyntaxException e) {
            saveHabits(new ArrayList<Habit>(), context);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            saveHabits(new ArrayList<Habit>(), context);
            // throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
        return result;
    }

    public void saveHabits(ArrayList<Habit> habits, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habits, writer);
            writer.flush();
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
