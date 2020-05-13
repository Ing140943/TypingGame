package application.windows;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Rule {

    private  int height = 400;
    private  int weight = 700;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    /**
     * The class explains the rules of this game.
     * Have the problem with the resolution of the picture( even use hugh resolution
     * but when execute the program picture not good as I expected.
     * @author Setthanat Kladee
     */
    public Rule(){
        mainPane = new AnchorPane();
        Image backgroundImage = new Image("Graphics/rules.png",700,650,true,false);
        BackgroundImage background = new BackgroundImage(backgroundImage
                , BackgroundRepeat.REPEAT
                ,BackgroundRepeat.REPEAT
                , BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
        Button backButton = new Button("Go back");
        backButton.setStyle("-fx-background-color: #28f5ff");
        backButton.setLayoutY(300);
        backButton.setLayoutX(10);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
                Main.window.show();
            }
        });
        mainPane.getChildren().add(backButton);
        mainScene = new Scene(mainPane,weight,height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.show();
    }
}
