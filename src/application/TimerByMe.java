package application;

import application.windows.PlayGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerByMe extends Pane {

    private Timeline animation;
    private int temp;
    private Label label;

    /**
     * Create timer for elapsed the time.
     * @param time the starter time for elapsed.
     */
    public TimerByMe(int time){
        temp = time;
        label = new Label("Time left"+ "\n"+String.valueOf(temp) + " seconds");
        label.setFont(Font.font(25));
        label.setAlignment(Pos.TOP_LEFT);
        getChildren().addAll(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeLabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.playFromStart();
    }

    /**
     * Show the time that is going now.
     * User can see how many time left.
     */
    public void timeLabel(){

        if (temp > 0 ){
            temp -= 1;
        }
        else if (temp == 0){
            Stage iu = PlayGame.getStage();
            iu.close();
            System.out.println("You loose leaw na");
            animation.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("You loose because your time is Out! \n You should type faster!");
            //wait for user to dismiss the dialog
            Main.window.show();
            alert.show();
        }
        String string = temp + "";
        label.setText("  Time left"+ "\n"+ string +" seconds");
    }

    /**
     * Force timer to stop running.
     */
    public void stopTime(){
        animation.stop();
    }
}
