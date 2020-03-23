package network;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RegularWorkout;
import model.Workout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;

public class ReadWorkoutsList  {
    protected HashMap<String, String> workouts;
    public ObservableList<Workout> workoutList;
    private String websiteToFetch = "http://msaubc.com/test/workouts.html";

    public ReadWorkoutsList() {
        workouts = new HashMap<>();
        workoutList = FXCollections.observableArrayList();
    }

    // EFFECTS: fetches line by line from the website
    public void createWorkoutVideo() throws IOException {
        BufferedReader br = null;
        try {
            String theURL = websiteToFetch;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                assignValues(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: populates the workout fields from the fetched website data
    private void assignValues(String line) {
        String[] x = line.split(" ");
        String[] y = line.split("\t");
        Workout newWorkout = new RegularWorkout(x[0], LocalDate.now());
        newWorkout.workout1 = y[1];
        newWorkout.workout2 = y[2];
        newWorkout.workout3 = y[3];
        newWorkout.workout4 = y[4];
        newWorkout.workout5 = y[5];
        workoutList.add(newWorkout);
        String[] z = x[1].split("\"");
        newWorkout.url = z[1];
        workouts.put(x[0], z[1]);

    }
}
