package application.windows;

import application.GenerateVocab;
import application.Main;
import application.TimerByMe;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;


import static javafx.scene.paint.Color.*;


public class PlayGame extends HBox {
    private int countWords = 0;  // for determine the time
    private int positionNow;              // tell what letters we are now
    private Scene mainScene;
    private static Stage gameStage;
    private Rectangle health;
    private static double val = 200;   //Hp
    private BorderPane paneContainsAll;
    private TimerByMe clock;
    private Label text;
    private String showUser;
    private int time;
    private GenerateVocab getWords;
    private String currentString ;
    private FlowPane wordsForTyping = new FlowPane();
    private VBox content = new VBox(2);


    /**
     * Change window Gaming part when click start button.
     * @author Setthanat Kladee
     */
    public PlayGame(){
        val = 200;
        clock = new TimerByMe(10);
        showUser = "  Your health left " + val + "\n" + "  Your accuracy is "
                + (val/200)*100+ " %" +"\n"
                + "  You are playing Typing Game!"+
                "\n       ........................................" ;
        text = new Label(showUser);
        text.setTextFill(MAGENTA);
        text.setFont(new Font("Comic Sans MS",30));
        getWords = new GenerateVocab();
        getText();
        positionNow = 0;
        gameStage = new Stage();
        gameStage.setResizable(false);
        wordsForTyping.setAlignment(Pos.TOP_CENTER);
        content.setAlignment(Pos.TOP_CENTER);
        paneContainsAll = new BorderPane();
        paneContainsAll.setCenter(content);
        paneContainsAll.setBottom(text);
        Image backgroundImage = new Image("Graphics/pastel.jpg",1700,900,true,false);
        BackgroundImage  background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        paneContainsAll.setBackground(new Background(background));
        mainScene = new Scene(paneContainsAll,1000,500);
        gameStage.setScene(mainScene);
        mainScene.setOnKeyTyped(
                new EventHandler<KeyEvent>(){
                    public void handle(KeyEvent e){
                        String code = e.getCharacter();
                        checkLet(code);
                    }
                }
        );
        gameStage.show();
    }

    /**
     * Return stage of this class which can use in other class.
     * @return gameStage
     */
    public static Stage getStage(){
        return gameStage;
    }

    /**
     * Sum of the characters that user type and then fusion to the word.
     */
    public void getText(){

        if (countWords == 100){
            showDialog();
            Main.window.show();
            gameStage.close();
        }

        health = new Rectangle(100,300,30,val);
        content.getChildren().clear();
        content.getChildren().addAll(wordsForTyping);
        health.setFill(GREEN);
        health.setTranslateX(260);
        health.setTranslateY(-30);

        positionNow = 0;
        currentString = getWords.getLabel();
        if ( currentString == null ){
            Main.window.show();
            gameStage.close();
        }
        else{
            countWords += 1;
            clock.stopTime();
            if (countWords <= 25 ){
                time = 7;
            }
            else if(countWords >= 45){
                time = 6;
            }
            else {
                time = 5;
            }
            clock = new TimerByMe(time);
            clock.setTranslateX(50);
            content.getChildren().addAll(clock,health);
            System.out.println("word : " + countWords);
            drawWord(true);
        }

    }

    /**
     * Check inout from user that they type correct characters or not.
     * @param let the character that they type.
     */
    public void checkLet (String let){
        if (let.charAt(0) != (currentString.charAt(positionNow))){
            drawWord(false);

        }
        else{
            positionNow++ ;
            drawWord(true);
            health = new Rectangle(100,300,30,val);
            if( positionNow == currentString.length()){
                getText();

            }
        }
    }

    /**
     * Draw each character that user type.
     * @param correct point that user type correct or wrong.
     */
    public void drawWord(boolean correct){
        if (time == 0){
            val -= 20;
        }
        int p = 0;
        String word = currentString;
        wordsForTyping.getChildren().clear();
        for (String s : word.split("")){
            Label l = new Label( s );
            if (p < positionNow){
                l.setTextFill(Color.GREEN);
            }
            if ( p == positionNow){
                if (correct){
                    l.setTextFill(Color.BLUE);
                }
                else{
                    val -= 5 ;
                    text.setText("  Your health left " + val + "\n" + "  Your accuracy is "
                            + (val/200)*100+ " %" +"\n"
                            + "  You are playing Typing Game!"+
                            "\n       ........................................") ;
                    paneContainsAll.setBottom(text);
                    if ( val <= 0 ){
                        clock.stopTime();
                        showDialog();
                        Main.window.show();
                        gameStage.close();
                    }
                    l.setTextFill(Color.RED);
                    System.out.println(val);

                }
            }
            if ( p > positionNow){
                l.setTextFill(BLACK);
            }
            l.setPrefWidth(20);
            l.setAlignment( Pos.CENTER );
            l.setFont( new Font("Arial",50));
            wordsForTyping.getChildren().addAll(l);
            p++;
        }

    }

    /**
     * Show some text when you lose or win game.
     */
    public void showDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (countWords < 100){
            alert.setTitle("Game Over");
            alert.setHeaderText("You loose because your helth is Over! \n You have to practice more!");

        }
        else{
            alert.setTitle("Congratulations!!");
            alert.setHeaderText("You won this game!!!");

        }
        clock.stopTime();
        alert.showAndWait();
    }

}