package application;

import application.windows.PlayGame;
import application.windows.Rule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView  implements property{

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    /**
     * Main page of Typing Game, show three menus for the player to choose.
     * @author Setthant Kladee
     */
    public GameView(){
        mainPane = new AnchorPane();
        int height = 400;
        int weight = 700;
        mainScene = new Scene(mainPane, weight, height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);   // cannot in full screen to hard for looking and not beautiful UI
        Button startButton = createButton("Start Typing!",305,50);
        Button rulesButton = createButton("Rules",322,100);
        Button existButton = createButton("Exist",325,150);
        startButton.setOnAction(e->
        {
            Main.window.hide();
            PlayGame playGame = new PlayGame();

        });
        rulesButton.setOnAction( e->
        {
            Main.window.hide();
            Rule rule = new Rule();
        });
        existButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Text typingGame = createText("! Typing \n Game ! ",60,100);
        mainPane.getChildren().addAll(startButton,rulesButton,existButton,typingGame);
        createBackground();

    }

    /**
     * Return main stage of this class.
     * @return maiStage
     */
    public Stage getMainStage(){
        return mainStage;
    }

    /**
     * Create button which property that I want.
     * @param message told the user what this button do
     * @param positionX position of X-axis
     * @param positionY position of Y-axis
     * @return button
     */
    @Override
    public Button createButton(String message, int positionX, int positionY ){
        Button button = new Button(message);
        button.setStyle("-fx-background-color: #28f5ff");
        button.setLayoutX(positionX);
        button.setLayoutY(positionY);
        return button;
    }

    /**
     * Create text for show in the pane.
     * @param message text that show on the window.
     * @param positionX position of X-axis
     * @param postionY position of Y-axis
     * @return text
     */
    @Override
    public Text createText(String message, int positionX, int postionY){
        Text text = new Text(message);
        text.setFont(new Font("Arial",48));
        text.setFill(Color.LIGHTPINK);
        text.setX(positionX);
        text.setY(postionY);
        return text;

    }

    /**
     * Set background from the picture.
     */
    public void createBackground() {
        Image backgroundImage = new Image("Graphics/cute-background2.jpg",1280,720,true,false);
        BackgroundImage  background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
    }

}
