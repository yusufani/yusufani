package GUI;

import Otopark.AutoPark;
import Otopark.Date;
import Otopark.Subscription;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerForAddSubcriber {

    @FXML
    private TextField enterPlate;

    @FXML
    private JFXDatePicker beginDate;


    @FXML
    private Label errorMesseage;

    @FXML
    private Label errorMessageInputs;

    @FXML
    private JFXCheckBox checkBeginDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXCheckBox checkEndDate;

    private Date subscriberBeginDate;
    private Date subscriberEndDate;

    @FXML
    void addSubscriberButton() {
        String plate = null;
        boolean everythingIsOk = true;
        try {
            errorMesseage.setVisible(false);
            errorMessageInputs.setVisible(false);
            if (checkBeginDate.isSelected()) {
                subscriberBeginDate = Date.getToday();
            } else {
                subscriberBeginDate = new Date(beginDate.getValue().getDayOfMonth(), beginDate.getValue().getMonthValue(), beginDate.getValue().getYear());
            }
            if (checkEndDate.isSelected()) {
                subscriberEndDate = Date.getToday();
            } else {
                subscriberEndDate = new Date(endDate.getValue().getDayOfMonth(), endDate.getValue().getMonthValue(), endDate.getValue().getYear());
            }
            plate = enterPlate.getText().toUpperCase();
            //   Time subcriberBeginTime = new Time(beginTime.getValue().getHour(),beginTime.getValue().getMinute(),subscriberBeginDate);
            //  Time subscriberEndTime = new Time (endTime.getValue().getHour(),endTime.getValue().getMinute(),subscriberEndDate);
        } catch (NullPointerException e) {
            e.printStackTrace();
            everythingIsOk = false;
            errorMessageInputs.setVisible(true);
        }

        if (everythingIsOk) {
            try {
                Subscription subscription = new Subscription(subscriberBeginDate, subscriberEndDate, plate);
                if (AutoPark.getInstance().addVehicle(subscription.getVehicle()))
                    close();
                else {
                    everythingIsOk = false;
                    errorMesseage.setStyle("-fx-fill: red; -fx-font-size: 16px;");
                    errorMesseage.setText("This Vehicle Has Been Aldready Rolleds");
                    errorMesseage.setVisible(true);
                }
            } catch (Exception e) {
                everythingIsOk = false;
                errorMesseage.setStyle("-fx-fill: red; -fx-font-size: 16px;");
                errorMesseage.setText(e.getMessage());
                errorMesseage.setVisible(true);
            }
        }
    }

    @FXML
    void close() {
        Stage stage = (Stage) errorMesseage.getScene().getWindow();
        stage.close();

    }

    @FXML
    void checkBox1() {
        if (checkBeginDate.isSelected())
            beginDate.setPromptText(Date.getToday().toString());
    }

    @FXML
    void checkBox2() {
        if (checkEndDate.isSelected())
            endDate.setPromptText(Date.getToday().toString());
    }

    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addSubscriberButton();
        }
    }
}


