package model;

import java.time.LocalDate;
import java.util.Date;

public class TodayWorkout extends Workout {
    public TodayWorkout(String name, LocalDate dueDate) {
        super(name, dueDate);
        this.workoutToday = true;
    }

    //MODIFIES: this
    //EFFECTS: sets the workout to not due today
    @Override
    public boolean dueToday() {
        return workoutToday;
    }
}
