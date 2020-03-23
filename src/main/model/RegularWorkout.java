package model;

import java.time.LocalDate;

public class RegularWorkout extends Workout {
    public RegularWorkout(String name, LocalDate dueDate) {
        super(name, dueDate);
        this.workoutToday = false;
    }

    //MODIFIES: this
    //EFFECTS: sets the workout to due today
    @Override
    public boolean dueToday() {
        return workoutToday;
    }
}
