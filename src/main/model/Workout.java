package model;

import exceptions.TooManyWorkouts;

import java.time.LocalDate;
import java.util.*;

public abstract class Workout {
    private String name;
    private Boolean status; // true is done, false not done
    private DateManager dateManager;
    public String workout1;
    public String workout2;
    public String workout3;
    public String workout4;
    public String workout5;
    public String url;

    protected boolean workoutToday;
    protected List<WorkoutList> availableWorkouts;
    protected Map<String, List<WorkoutList>> workoutsCategory;

    // MODIFIES: this
    // EFFECTS: creates a new workout with name and dueDate, and
    //          sets status to not done
    public Workout(String name, LocalDate dueDate) {
        this.name = name;
        this.dateManager =  new DateManager(dueDate);
        this.status = false;
        availableWorkouts = new ArrayList<>();
        workoutsCategory = new HashMap<>();
        workoutsCategory.put(name, availableWorkouts);

    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with name and dueDate to current date
    public Workout(String name) {
        this.name = name;
        this.dateManager = new DateManager(LocalDate.now());
        this.status = false;
        availableWorkouts = new ArrayList<>();
        workoutsCategory = new HashMap<>();
        workoutsCategory.put(name, availableWorkouts);

    }

    // EFFECTS: returns the workout name
    public String getName() {
        return name;
    }

    // EFFECTS: returns status of the workout task
    public Boolean getStatus() {
        return status;
    }

    // EFFECTS: returns date workout is due
    public LocalDate getDueDate() {
        return dateManager.getDueDate();
    }

    // Requires: a finished workout
    // EFFECTS: returns date workout is due
    public LocalDate getFinishedDate() {
        if (this.status) {
            return dateManager.getFinishedDate();
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the workout name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: toggles the status of the workout
    public void changeStatus() {
        this.status = !this.status;
        if (this.status) {
            dateManager.setFinishedDate(LocalDate.now());
        }
    }

    // REQUIRES: a later date before today's date
    // MODIFIES: this
    // EFFECTS: changes the due date of the workout
    public void setDueDate(LocalDate dueDate) {
//        Date today = new Date();
//        if (today.before(dueDate)) {
        this.dateManager.setDueDate(dueDate);
//            return "Success";
//        } else {
//            return "date should be chosen after today's date";
//        }
    }

    // MODIFIES: WorkoutList
    // EFFECTS: adds this to a workout List
    public void addWorkoutList(WorkoutList workoutList) throws TooManyWorkouts {
        if (!workoutsCategory.get(name).equals(workoutList)) {
            List<WorkoutList> list = workoutsCategory.get(name);
            workoutsCategory.put(name, list);
            workoutList.addWorkout(this);
        }

    }


    public abstract boolean dueToday();

    // EFFECTS: checks in the name of the workouts are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workout workout = (Workout) o;
        return name.equals(workout.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // EFFECTS: returns the name of the workout
    @Override
    public String toString() {
        return this.getName();
    }
}

