package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {

    @FXML
    private Label TextHourlyFee;

    @FXML
    private Label TextCurrentTime;

    @FXML
    private Label TextDailyIncome;

    @FXML
    private Label TextCapacity;

    @FXML
    private Label TextCurrentParkedVehicle;


    @FXML
    void showSubcribedVehicles(ActionEvent event) {
        Stage stage = (Stage) TextHourlyFee.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("isParked.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root));
        stage2.getScene().setFill(Color.TRANSPARENT);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.show();
    }

}
