package sample;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerForSplashScreen {

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;

    public void initialize(){
        setRotate(circle1, true, 360, 1,-150,0);
        setRotate2(circle2, true, 180, 1,0,-150);
        setRotate2(circle3, true, 145, 1,150,0);
        setRotate2(circle4, true, 145, 1,0,150);
    }

    private void setRotate(Circle circle,boolean isReverse,int angle, double duration, int coordinatX ,int coordinatY) {
        TranslateTransition rotateTransition = setRotateMethod(circle, isReverse, angle, duration,coordinatX,coordinatY);
        rotateTransition.setOnFinished(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) circle1.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            primaryStage.setAlwaysOnTop(true);
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.getScene().setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        });

    }

    private void setRotate2(Circle circle,boolean isReverse,int angle, double duration,int coordinatX, int coordinatY){
        setRotateMethod(circle, isReverse, angle, duration,coordinatX,coordinatY);
    }

    private TranslateTransition setRotateMethod(Circle circle, boolean isReverse, int angle, double duration,int coordinatX,int coordinatY) {
       /* RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), circle);
        rotateTransition.setAutoReverse(isReverse);
        rotateTransition.setByAngle(angle);
*/
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), circle);
        translateTransition.setAutoReverse(isReverse);
        translateTransition.setFromY(circle.getTranslateY());
        if (coordinatX!= 0 || coordinatY != 0 ){
            if(coordinatX != 0) {
                translateTransition.setToX(coordinatX);
            }
            else {
                translateTransition.setToY(coordinatY);
            }
        }
        translateTransition.setDelay(Duration.seconds(0));
        translateTransition.setCycleCount(2);
        translateTransition.play();
/*
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(3);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();*/
        return translateTransition;
    }
    }