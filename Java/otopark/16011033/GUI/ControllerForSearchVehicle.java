package GUI;

import Otopark.AutoPark;
import Otopark.SubscribedVehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        SubscribedVehicle subscribedVehicle=AutoPark.getInstance().searchVehicle(enterPlate.getText().toUpperCase());
        if(subscribedVehicle == null ){
            ErrorMessage.setVisible(true);
            ErrorMessage.setText("No records with this(\""+enterPlate.getText().toUpperCase()+"\") license plate found ");
        }
        else{
            HeaderVehicleInfo.setVisible(true);
            vehicleInfo.setVisible(true);
            vehicleInfo.setText(subscribedVehicle.toString()+"\nSubscription Begin and End Date :\n "+subscribedVehicle.getBegin().toString()+" "+subscribedVehicle.getEnd().toString());
        }
    }
    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }
}
