package GUI.Controllers;

import Otopark.Date;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerForSetCurrentTime {

    @FXML
    private Text resultofPark;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    void set( ) {
        boolean flag=true;
        try{
            Date date = new Date(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue(), datePicker.getValue().getYear());
            Date.setToday(date);
        }
    catch (Exception e ){
            e.printStackTrace();
            flag=false;
    }
        if(flag) {
            Controller.getInstance().updateCurrentTime();
            exit();
        }
    }
    void exit( ) {
        Stage stage =(Stage) datePicker.getScene().getWindow();
        stage.close();

    }
}
