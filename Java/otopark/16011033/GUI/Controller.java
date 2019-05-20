package GUI;
import Otopark.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static Controller controller =null;
    public static Controller getInstance(){ return controller;}
    public static boolean isSetCapacity=false;

    public static boolean getIsSetCapacity() {
        return isSetCapacity;
    }


    @FXML
    private Label TextHourlyFee;

    @FXML
    private Label TextCurrentTime;

    @FXML
    private Label TextDailyIncome;

    @FXML
    private Label TextCapacity ;

    @FXML
    private Label TextCurrentParkedVehicle;
    @FXML
    void showAddSubscriber() {
        openStageAndFxml("addSubscriber.fxml");
    }

    @FXML
    void showIsParked() {
        openStageAndFxml("isParked.fxml");
    }

    @FXML
    void showSearchVehicle() {
        openStageAndFxml("searchVehicle.fxml");
    }

    @FXML
    void showSetCapacity(ActionEvent event) throws IOException {
        isSetCapacity = true;
        openStageAndFxml("setAttributesAutopark.fxml");

    }
    @FXML
    void showSetCurrentTime() {
        openStageAndFxml("currentTime.fxml");
    }

    @FXML
    void showSetHourlyFee() {
        isSetCapacity=false;
        openStageAndFxml("setAttributesAutopark.fxml");

    }

    @FXML
    void showSubcribedVehicles() {
        openStageAndFxml("subscribedWindows.fxml");
    }
    @FXML
    void showVehicleEnters() {
        openStageAndFxml("vehicleEnters.fxml");
    }

    @FXML
    void showVehicleExits() {
        openStageAndFxml("vehicleExits.fxml");
    }
    @FXML
   public void exit() {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller=this;
        try {
    
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     @FXML
    public void updateHourlyFee(){
        TextHourlyFee.setText("Hourly Fee : " +AutoPark.getInstance().getHourlyFee());
    }
    public void updateCurrentTime(){
        TextCurrentTime.setText("Current Time : " + Date.getToday().toString());
    }
    public void updateDailyIncome(){
        TextDailyIncome.setText("Daily Income : "+ AutoPark.getInstance().getIncomeDaily());
    }
    public void updateCurrentParkedVehicle(){
        TextCurrentParkedVehicle.setText("Current Parked Vehicle : " +AutoPark.getInstance().getCurrentParkedCount());
    }
    public void updateCapacity(){
        TextCapacity.setText("Capacity : "+AutoPark.getInstance().getCapacity());
    }

    public void init() {
        updateCapacity();
        updateCurrentTime();
        updateDailyIncome();
        updateHourlyFee();
        updateCurrentParkedVehicle();
    }
    public void openStageAndFxml(String fxmlName){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root));
        stage2.getScene().setFill(Color.TRANSPARENT);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.centerOnScreen();
        stage2.show();
    }
    }
