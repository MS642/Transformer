package ui;

import exceptions.TooManyWorkouts;
import model.RegularWorkout;
import model.TodayWorkout;
import model.Workout;
import model.WorkoutList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TodoWorkouts {
    private final int functionNum = 5;
    private int counter;
    private Scanner sc;

    // EFFECTS: initializes a workout list object
    public TodoWorkouts() {
        this.counter = 0;
    }

    // EFFECTS: prints the welcome screen and starts waiting for the user input
    public void run() {
        System.out.println("Welcome to Workout version 1.3");
        System.out.println("====================================");
        System.out.println("add a to do list item..................[1]");
        System.out.println("cross off an item......................[2]");
        System.out.println("show all the items.....................[3]");
        System.out.println("save list..............................[4]");
        System.out.println("load previous list.....................[5]");
        System.out.println("To quit app....[q]\n");
        start();
    }

    // EFFECTS: takes user inputs and add or remove workouts
    private void start() {
        sc = new Scanner(System.in);
        WorkoutList workoutList = new WorkoutList(5, "todo list");
        String input;
        boolean quit = false;
        while (!quit) {
            System.out.println("====================================");
            input = sc.nextLine();
            quit = checkInput(input, workoutList);
        }
    }

    private boolean checkInput(String input, WorkoutList workoutList) {
        if (input.equals("1")) {
            addWorkout(workoutList);
        } else if (input.equals("2")) {
            removeWorkout(workoutList);
        } else if (input.equals("3")) {
            printWorkouts(workoutList);
        } else if (input.equals("4")) {
            saveList(workoutList);
        } else if (input.equals("5")) {
            loadList(workoutList);
        } else {
            System.out.println("quitting");
            return true;
        }
        return false;
    }

    // MODIFIES: WorkoutList
    // EFFECTS: adds a workout to the workoutList
    private void addWorkout(WorkoutList workoutList) {
        System.out.println("Enter the Workout name:");
        String workoutName = sc.nextLine();
        System.out.println("Workout due in how many days?");
        int workoutDaysDue = sc.nextInt();
        sc.nextLine();
        LocalDate today = LocalDate.now();
        LocalDate dueDate = LocalDate.now().plusDays(workoutDaysDue);
        boolean added = true;
        listChanged(workoutName, added);
        try {
            if (workoutDaysDue == 0) {
                workoutList.addWorkout(new TodayWorkout(workoutName, today));
            } else {
                workoutList.addWorkout(new RegularWorkout(workoutName, dueDate));
            }
        } catch (TooManyWorkouts tooManyWorkouts) {
            System.out.println("Can't exceed the number of workouts in that day");
        }
    }

    private void listChanged(String workoutName, boolean added) {
        if (added) {
            System.out.println("Workout " + workoutName + " has been added to the list");
        } else {
            System.out.println("Workout " + workoutName + " has been removed from the list");
        }
    }

    // MODIFIES: WorkoutList
    // EFFECTS: removes a workout from the workoutList
    private void removeWorkout(WorkoutList workoutList) {
        System.out.println("Enter workout number to be removed");
        int index = sc.nextInt();
        sc.nextLine();
//        System.out.println(workoutList.getTask(index).getName());
        boolean added = false;
        listChanged(workoutList.getTask(index).getName(), added);
        workoutList.removeWorkout(index);
    }


    // EFFECTS: prints out the workouts in our list
    private void printWorkouts(WorkoutList workoutList) {
        counter = 0;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        for (Workout workout : workoutList.getWorkouts()) {
            System.out.println(counter++ + "- " + workout.getName() + " Due Date: " + df.format(workout.getDueDate()));
        }
    }

    private void saveList(WorkoutList workoutList) {
        try {
            workoutList.save();
        } catch (Exception e) {
            System.out.println("file not Found");
        }

    }

    private void loadList(WorkoutList workoutList) {
        try {
            workoutList.load();
        } catch (Exception e) {
            System.out.println("failed to load");
        }
    }

}
