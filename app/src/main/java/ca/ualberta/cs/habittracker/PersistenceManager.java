package ca.ualberta.cs.habittracker;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
/**
 * Created by L on 9/27/2016.
 */
public class PersistenceManager {

    private static final String FILENAME = "tommy3habittracker.sav";

    public PersistenceManager() {

    }

    public ArrayList<Habit> LoadHabits() {
        ArrayList<Habit> result = new ArrayList<Habit>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22, 2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            result = gson.fromJson(in, listType);
            in.close();
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
        return result;
    }

    public void SaveHabits(ArrayList<Habit> habits) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
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
