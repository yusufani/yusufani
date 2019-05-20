package GUI;

import Otopark.AutoPark;
import Otopark.Time;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
           if( AutoPark.getInstance().vehicleEnters(enterPlate.getText().toUpperCase(), new Time(enterTimePicker.getValue().getHour(),enterTimePicker.getValue().getMinute()),isOfficialCar.isSelected())){
                Controller.getInstance().updateCurrentParkedVehicle();
                   close();
        }
           else{
               ErrorMessage.setVisible(true);
               ErrorMessage.setStyle("-fx-fill: red; -fx-font-size: 16px;");
               if(AutoPark.getInstance().getCurrentParkedCount()+1>AutoPark.getInstance().getCapacity()){
                   ErrorMessage.setText("Capacity Full ");

               }else {
                   ErrorMessage.setText("Vehicle has already Parked ");
               }
           }


    }

    @FXML
    void close( ) {
       Stage stage = (Stage) ErrorMessage.getScene().getWindow();
       stage.close();
    }
    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            add();
        }
    }
}
