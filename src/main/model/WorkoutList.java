package model;

import exceptions.TooManyWorkouts;
import io.Loadable;
import io.Savable;
import javafx.collections.ObservableList;
import network.GetWorkoutVideo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class WorkoutList extends Observable implements Loadable, Savable {
    private int numOfWorkouts;
    private String workoutListName;

    private int workoutsInList;
    private List<Workout> workouts;
    public ObservableList<Workout> workoutTodoList;
    private String file = "savedList.txt";


    // MODIFIES: this
    // EFFECTS: creates a workout list with a number of workouts in the list and the list name
    public WorkoutList(int numOfWorkouts, String workoutListName) {
        this.numOfWorkouts = numOfWorkouts;
        this.workoutListName = workoutListName;
        this.workouts = new ArrayList<>();
        this.workoutsInList = 0;
        GetWorkoutVideo gt = new GetWorkoutVideo();
        workoutTodoList = gt.workoutList;
        this.addObserver(gt);
    }

    // MODIFIES: this
    // EFFECTS: sets number of workouts in the list
    public void setNumOfWorkouts(int numOfWorkouts) {
        this.numOfWorkouts = numOfWorkouts;
    }

    // EFFECTS: gets the number of workouts currently in the list
    public int getWorkoutsInList() {
        this.workoutsInList = workouts.size();
        return workoutsInList;
    }

    // MODIFIES: this
    // EFFECTS: changes the workout list name
    public void setWorkoutListName(String workoutListName) {
        this.workoutListName = workoutListName;
    }


    // EFFECTS: returns number of workouts in the list
    public int getNumOfWorkouts() {
        return numOfWorkouts;
    }

    // EFFECTS: returns the name of the workout list
    public String getWorkoutListName() {
        return workoutListName;
    }


    // EFFECTS: returns workout based on index
    public Workout getTask(int x) {
        return workouts.get(x);
    }


    // MODIFIES: this
    // EFFECTS: adds a workout to the list
    //          throws an exception if the size is already full
    public void addWorkout(Workout w) throws TooManyWorkouts {
        if (workouts.size() == this.numOfWorkouts) {
            throw new TooManyWorkouts();
        } else {
            if (!workouts.contains(w)) {
                workouts.add(w);
                w.addWorkoutList(this);
                setChanged();
                notifyObservers(w.getName());

            }
        }
    }


    // MODIFIES: this
    // EFFECTS: adds a workout to the list
    //          throws an exception if the size is already full
    public void addWorkout(Workout w, String name) throws TooManyWorkouts {
        if (workouts.size() == this.numOfWorkouts) {
            throw new TooManyWorkouts();
        } else {
            this.workouts.add(w);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes workout from list
    public void removeWorkout(int x) {
        this.workouts.remove(x);
    }

    // EFFECTS: returns the list of workouts
    public List<Workout> getWorkouts() {
        return workouts;
    }

    // MODIFIES: workouts
    // EFFECTS: loads workouts from file and save them to workouts
    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));
        List<Workout> newWorkouts  = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(" ");
            List<String> workoutPrev = new ArrayList<>(Arrays.asList(splits));
//            System.out.println(line);
            Date date = null;
            try {
                date = new SimpleDateFormat("MMMM").parse(workoutPrev.get(2));
            } catch (ParseException e) {
                System.out.println("Failed to parse it");
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Workout workout = new RegularWorkout(workoutPrev.get(0), LocalDate.of(Integer.parseInt(workoutPrev.get(1)),
                    cal.get(Calendar.MONTH) + 1, Integer.parseInt(workoutPrev.get(3))));
            newWorkouts.add(workout);
        }
        workouts = newWorkouts;
    }

    // EFFECTS: saves the workouts to file
    @Override
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for (Workout w : this.workouts) {
            writer.println(w.getName() + " " + w.getDueDate().getYear() + " " + (w.getDueDate().getMonth()) + " "
                    + (w.getDueDate().getDayOfMonth()));
        }
        writer.close(); //note -- if you miss this, the file will not be written at all.
    }

    // MODIFIES: this
    // EFFECTS: removes workout from the workoutTodoList
    public void removeWorkoutTodoTest(Workout workout) {
        this.workoutTodoList.remove(workout);
    }
}
