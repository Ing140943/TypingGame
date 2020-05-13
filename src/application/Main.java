package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static Stage window;    // This window will affect to other option in other class, so I decide to make it public.

    /**
     * Run the Main to execute the Game.
     * @param primaryStage default stage
     */
    @Override
    public void start(Stage primaryStage)  {
        window = primaryStage;
        GameView gameView = new GameView();
        window = gameView.getMainStage();

        window.setTitle("TypingGame");
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
