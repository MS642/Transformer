package network;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GetWorkoutVideo extends ReadWorkoutsList implements Observer {

    public GetWorkoutVideo() {
        super();
        try {
            createWorkoutVideo();
        } catch (IOException e) {
            System.out.println("failed to access the website");
        }
    }

    // EFFECTS: prints the video url
    @Override
    public void update(Observable o, Object arg) {
        String args = (String) arg;
        System.out.println("The " + args + " workout video is " + workouts.get(args));
    }
}
