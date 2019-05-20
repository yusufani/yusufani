package GUI;

import Otopark.AutoPark;
import Otopark.Time;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerForVehicleExits {

    @FXML
    private TextField enterPlate;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXTimePicker enterTimePicker;
    @FXML
    private ImageView arrow;

    @FXML
    void exit() {
        arrow.setVisible(false);
        ErrorMessage.setVisible(false);
        try{if (AutoPark.getInstance().vehicleExits(enterPlate.getText().toUpperCase(), new Time(enterTimePicker.getValue().getHour(), enterTimePicker.getValue().getMinute()))) {
            Controller.getInstance().updateDailyIncome();
            Controller.getInstance().updateCurrentParkedVehicle();
            ErrorMessage.setVisible(true);
            ErrorMessage.setStyle("-fx-fill: green; -fx-font-size: 16px;");
            String plate = enterPlate.getText().toUpperCase();
            ErrorMessage.setText("Fee :"+AutoPark.getInstance().searchParkedArray(plate).getFee() +" Please Do not forget to collect ");
            arrow.setVisible(true);
        } else {
            ErrorMessage.setVisible(true);
            ErrorMessage.setStyle("-fx-fill: red; -fx-font-size: 16px;");
            ErrorMessage.setText("Vehicle Can not be found ");
        }}catch (Exception e ){
            System.out.println("Date once olamaz ");
            System.out.println("Hata mesajı : "+ e.getMessage());
            ErrorMessage.setVisible(true);
            ErrorMessage.setStyle("-fx-fill: red; -fx-font-size: 16px;");
            ErrorMessage.setText(e.getMessage());
        }
    }


    @FXML
    public void close() {
        Stage stage = (Stage) ErrorMessage.getScene().getWindow();
        stage.close();
    }
    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            exit();
        }
    }
}
