package GUI;

import Otopark.AutoPark;
import Otopark.Date;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.Key;

public class ControllerForLogin {
private static ControllerForLogin instance = new ControllerForLogin() ;
    @FXML
    private Text QuestionsText;
    @FXML
    private TextField enterHourlyFee=new TextField();

    @FXML
    private JFXButton enterSystem;

    @FXML
    private JFXDatePicker currentTime;

    @FXML
    private TextField enterCapacity;

    @FXML
    void enterTheSystem( ) {
        boolean flag = false ;
        try {
            Date date = new Date(currentTime.getValue().getDayOfMonth(), currentTime.getValue().getMonthValue(), currentTime.getValue().getYear());
            System.out.println(date);
            System.out.println("Date alındı");
            double hourlyFee = Double.parseDouble(enterHourlyFee.getText());
            System.out.println("Hourly Fee alindi");
            int capacity = Integer.parseInt(enterCapacity.getText());
            System.out.println("Capacity ");
            AutoPark.getInstance().setCapacity(capacity);
            AutoPark.getInstance().setHourlyFee(hourlyFee);
            Date.setToday(date);
            flag=true;
        }catch (Exception e ){
            e.printStackTrace();
            flag=false;
        }
        if(flag== true) {
            Stage stage = (Stage) enterHourlyFee.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.getScene().setFill(Color.TRANSPARENT);
            stage2.initStyle(StageStyle.TRANSPARENT);
            stage2.show();
        }else{
            if (enterHourlyFee.getText().equals("") ){
                enterHourlyFee.setPromptText("Can not Be Null");
            }
            if (enterCapacity.getText().equals("") ){
                enterCapacity.setPromptText("Can not Be Null");
            }

            if ( currentTime.getValue() == null ){
                currentTime.setPromptText("Cannot Be Null");
            }
        }
    }
    @FXML
    void showQuestions(ActionEvent event) {
        if(QuestionsText.isVisible())
            QuestionsText.setVisible(false);
        else  QuestionsText.setVisible(true);
    }
    @FXML

    public static ControllerForLogin getInstance(){
        return instance;
    }
    @FXML
    void exit() {
        Platform.exit();
    }
    @FXML
    void keyEnter(KeyEvent event) {
    if (event.getCode()== KeyCode.ENTER){
        enterTheSystem();
}
    }
}



