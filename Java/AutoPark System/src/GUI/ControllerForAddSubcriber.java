package GUI;

import Otopark.AutoPark;
import Otopark.Date;
import Otopark.Subscription;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        String plate=null;
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
             plate = enterPlate.getText();
         //   Time subcriberBeginTime = new Time(beginTime.getValue().getHour(),beginTime.getValue().getMinute(),subscriberBeginDate);
          //  Time subscriberEndTime = new Time (endTime.getValue().getHour(),endTime.getValue().getMinute(),subscriberEndDate);
        } catch (NullPointerException e) {
            e.printStackTrace();
            everythingIsOk=false;
            errorMessageInputs.setVisible(true);
        }

        if (everythingIsOk) {
            try {
                Subscription subscription = new Subscription(subscriberBeginDate,subscriberEndDate,plate);
                AutoPark.getInstance().addVehicle(subscription.getVehicle());
                close();
            }catch (Exception e) {
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
    }


