package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Workout;
import model.WorkoutList;

import java.util.Optional;

public class MainController {
    WorkoutList workoutList;
    @FXML
    private ListView<Workout> workoutListView;
    @FXML
    private Label workoutVideo;
    @FXML
    private CheckBox workout1;
    @FXML
    private CheckBox workout2;
    @FXML
    private CheckBox workout3;
    @FXML
    private CheckBox workout4;
    @FXML
    private CheckBox workout5;
    @FXML
    private ContextMenu muscleGroupContextMenu;
    @FXML
    private WebView webView;



    @FXML
    private void initialize() {
        muscleGroupContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem();
        deleteMenuItem.setText("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Workout workout = workoutListView.getSelectionModel().getSelectedItem();
                deleteMuscleGroup(workout);
            }
        });
        muscleGroupContextMenu.getItems().addAll(deleteMenuItem);
        startListView();


    }

    private void startListView() {
        workoutListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Workout>() {
            @Override
            public void changed(ObservableValue<? extends Workout> observable, Workout oldValue, Workout newValue) {
                if (newValue != null) {
                    handleClicks();
                    handleWebsite();
                }
            }
        });

        workoutList = new WorkoutList(5, "todo list");
        ObservableList<Workout> workouts = workoutList.workoutTodoList;
        workoutListView.setItems(workouts);
        workoutListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        workoutListView.getSelectionModel().selectFirst();

        workoutListView.setContextMenu(muscleGroupContextMenu);
    }

    @FXML
    private void deleteMuscleGroup(Workout workout) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Muscle Group");
        alert.setHeaderText("Delete muscle group: " + workout.getName());
        alert.setContentText("Are you sure that is not the muscle you wanna train? "
                + "want to remove it?");
        Optional<ButtonType> buttonPressed = alert.showAndWait();

        if (buttonPressed.isPresent() && (buttonPressed.get() == ButtonType.OK)) {
            workoutList.removeWorkoutTodoTest(workout);
        }
        workoutListView.setItems(workoutList.workoutTodoList);
    }

    @FXML
    private void handleClicks() {
        Workout workout = workoutListView.getSelectionModel().getSelectedItem();
        workout1.setText((workout.workout1));
        workout2.setText((workout.workout2));
        workout3.setText((workout.workout3));
        workout4.setText((workout.workout4));
        workout5.setText((workout.workout5));
    }

    @FXML
    private void handleWebsite() {
        WebEngine engine = webView.getEngine();
        Workout workout = workoutListView.getSelectionModel().getSelectedItem();
        engine.load(workout.url);
    }

    @FXML
    private void handleButtonClick() {
        workout1.setSelected(false);
        workout2.setSelected(false);
        workout3.setSelected(false);
        workout4.setSelected(false);
        workout5.setSelected(false);

    }

    @FXML
    private void handleEdex() {
        WebEngine engine = webView.getEngine();
        Workout workout = workoutListView.getSelectionModel().getSelectedItem();
        engine.load("https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/f008e11da76e491786a63d1e4ef70a3d/");
    }

    @FXML
    private void restTimer() {
        WebEngine engine = webView.getEngine();
        Workout workout = workoutListView.getSelectionModel().getSelectedItem();
        engine.load("http://www.online-timers.com/timer-2-minutes?time=223124755328");
    }
}
