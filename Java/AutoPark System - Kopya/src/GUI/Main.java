package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
            primaryStage.setTitle("AutoPark System");
            primaryStage.setScene(new Scene(root));
            primaryStage.getScene().setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        }
}
