package model;

import model.RegularWorkout;
import model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegularWorkout {
    Workout workout;

    @BeforeEach
    void runBefore() {
        this.workout = new RegularWorkout("curls", LocalDate.now());
    }

    @Test
    void testWorkoutStatus() {
        assertFalse(workout.getStatus());
        workout.changeStatus();
        assertTrue(workout.getStatus());
    }

    @Test
    void testWorkoutName() {
        workout.setName("Shoulders Workout");
        assertEquals(0, workout.getName().compareTo("Shoulders Workout"));
        workout.setName("Arms Workout");
        assertEquals(0, workout.getName().compareTo("Arms Workout"));
    }

    @Test
    void testDueDate() {
        LocalDate today = LocalDate.now();
        workout.setDueDate(today);
        assertTrue(today.equals(workout.getDueDate()));
        assertFalse(workout.dueToday());
        //Data today1 = new Date();
//        Date dueDate = new Date(today.getYear(), today.getMonth(), today.getDay() - 5);
//        String output = workout.setDueDate(dueDate);
//        assertEquals("date should be chosen after today's date", output);
    }

    @Test
    void testCheckStatus(){
        assertFalse(workout.getStatus());
        workout.changeStatus();
        assertEquals(LocalDate.now(), workout.getFinishedDate());
        assertTrue(workout.getStatus());
        workout.changeStatus();
        assertFalse(workout.getStatus());
        assertEquals(null, workout.getFinishedDate());
    }

}
