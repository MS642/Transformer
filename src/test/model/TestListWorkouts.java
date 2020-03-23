package model;

import exceptions.TooManyWorkouts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestListWorkouts {
    WorkoutList workoutList;

    @BeforeEach
    void runBefore() {
        this.workoutList = new WorkoutList(5, "Legs Workout");
    }

    @Test
    void testWorkoutNum() {
        assertEquals(5, workoutList.getNumOfWorkouts());
        workoutList.setNumOfWorkouts(10);
        assertEquals(10, workoutList.getNumOfWorkouts());


    }
    @Test
    void testWorkoutName() {
        workoutList.setWorkoutListName("Shoulders Workout");
        assertEquals(0, workoutList.getWorkoutListName().compareTo("Shoulders Workout"));
        workoutList.setWorkoutListName("Arms Workout");
        assertEquals(0, workoutList.getWorkoutListName().compareTo("Arms Workout"));
    }

    @Test
    void testAddRemoveWorkout(){
        Workout workout1 = new RegularWorkout("curls", LocalDate.now());
        Workout workout4 = new RegularWorkout("arms", LocalDate.now());
        Workout workout5 = new RegularWorkout("legs", LocalDate.now());
        try {
            workoutList.addWorkout(workout1);
        } catch (TooManyWorkouts tooManyWorkouts) {
            System.out.println("Can't exceed the number of workouts in that day");
        }
        assertEquals(1, workoutList.getWorkoutsInList());
        Workout workout2 = new RegularWorkout("abs", LocalDate.now());
        try {
            workoutList.addWorkout(workout2);
        } catch (TooManyWorkouts tooManyWorkouts) {
            System.out.println("Can't exceed the number of workouts in that day");
        }
        assertEquals(2, workoutList.getWorkoutsInList());
        Workout workout3 = new RegularWorkout("chest", LocalDate.now());
        try {
            workoutList.addWorkout(workout3);
        } catch (TooManyWorkouts tooManyWorkouts) {
            System.out.println("Can't exceed the number of workouts in that day");
        }
        assertEquals(3, workoutList.getWorkoutsInList());
        workoutList.removeWorkout(1);
        assertEquals(2, workoutList.getWorkoutsInList());
        workoutList.removeWorkout(1);
        assertEquals(1, workoutList.getWorkoutsInList());
        try {
            workoutList.addWorkout(workout1);
            workoutList.addWorkout(workout2);
            workoutList.addWorkout(workout3);
            workoutList.addWorkout(workout4);
            workoutList.addWorkout(workout5);
            fail("test failed");
        } catch (TooManyWorkouts tooManyWorkouts) {
            System.out.println("Can't exceed the number of workouts in that day");
        } finally {
            System.out.println("Test Complete");
        }

    }

    @Test
    void testLoadSave(){
        LocalDate today = LocalDate.now();
        //LocalDate;
        LocalDate dueDate = LocalDate.now().plusDays(3);
        WorkoutList loadedWorkoutList = new WorkoutList(5, "arms Workout");
        Workout workout1 = new RegularWorkout("curls", dueDate);
        try {
            workoutList.addWorkout(workout1);
        } catch (TooManyWorkouts tooManyWorkouts) {
            fail("Can't exceed the number of workouts in that day");
        }
        Workout workout2 = new RegularWorkout("abs",  dueDate);
        try {
            workoutList.addWorkout(workout2);
        } catch (TooManyWorkouts tooManyWorkouts) {
            fail("Can't exceed the number of workouts in that day");
        }
        try {
            workoutList.save();
            loadedWorkoutList.load();

            for(int i = 0 ; i < (loadedWorkoutList.getWorkouts().size()); i++){
                assertTrue(loadedWorkoutList.getWorkouts().get(i).getName().equals(workoutList.getWorkouts().get(i).getName()));
                System.out.println(workoutList.getWorkouts().get(i).getDueDate());
                System.out.println(loadedWorkoutList.getWorkouts().get(i).getDueDate());

                assertTrue(loadedWorkoutList.getWorkouts().get(i).getDueDate().equals(workoutList.getWorkouts().get(i).getDueDate()));
            }
        } catch (FileNotFoundException e){
            fail("File Not Found");
        } catch (UnsupportedEncodingException e){
            fail("The Character Encoding is not supported.");
        } catch (IOException e){
            fail("Failed or interrupted during I/O operations.");
        } finally {
            System.out.println("Test complete");
        }
    }

}
