package GUI;

import Otopark.AutoPark;
import Otopark.Time;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerForVehicleEnters {

    @FXML
    private TextField enterPlate;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXTimePicker enterTimePicker;

    @FXML
    private CheckBox isOfficialCar;

    @FXML
    void add( ) {
        ErrorMessage.setVisible(false);
           if( AutoPark.getInstance().vehicleEnters(enterPlate.getText(), new Time(enterTimePicker.getValue().getHour(),enterTimePicker.getValue().getMinute()),isOfficialCar.isSelected())){
               close();
        }
           else{
               ErrorMessage.setVisible(true);
               ErrorMessage.setStyle("-fx-fill: red; -fx-font-size: 16px;");
               ErrorMessage.setText("Vehicle has already Parked ");
           }


    }

    @FXML
    void close( ) {
       Platform.exit();
    }

}
