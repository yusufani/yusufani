package GUI.Controllers;

import Otopark.AutoPark;
import Otopark.SubscribedVehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerForSearchVehicle {

    @FXML
    private TextField enterPlate;

    @FXML
    private Text resultofPark;

    @FXML
    private Label HeaderVehicleInfo;

    @FXML
    private Label vehicleInfo;

    @FXML
    private Label ErrorMessage;

    @FXML
    void exit( ) {
        Stage stage =(Stage) enterPlate.getScene().getWindow();
        stage.close();
    }

    @FXML
    void search( ) {
        HeaderVehicleInfo.setVisible(false);
        vehicleInfo.setVisible(false);
        ErrorMessage.setVisible(false);
        SubscribedVehicle subscribedVehicle=AutoPark.getInstance().searchVehicle(enterPlate.getText());
        if(subscribedVehicle == null ){
            ErrorMessage.setVisible(true);
        }
        else{
            HeaderVehicleInfo.setVisible(true);
            vehicleInfo.setVisible(true);
            resultofPark.setStyle("-fx-fill: #77ff9e ; -fx-font-size: 16px;");
            vehicleInfo.setText(subscribedVehicle.toString());
        }
    }

}
