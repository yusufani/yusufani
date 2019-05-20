package GUI;

import Otopark.AutoPark;
import Otopark.ParkRecord;
import Otopark.SubscribedVehicle;
import Otopark.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForSubscribedTable implements Initializable  {
    @FXML
    private TableView<SubscribedVehicle> table_subs;

    @FXML
    private TableColumn<SubscribedVehicle, String> col_Plate;

    @FXML
    private TableColumn<SubscribedVehicle, String> col_Begin;

    @FXML
    private TableColumn<SubscribedVehicle, String> col_end;

    @FXML
    private TableView<ParkRecord> table_records;

    @FXML
    private TableColumn<Vehicle, String> col_Vehicle;

    @FXML
    private TableColumn<Vehicle, String> col_start;

    @FXML
    private TableColumn<Vehicle, String> col_endTime;


    public ObservableList<SubscribedVehicle> bringVehicle(){
        ObservableList<SubscribedVehicle > vecOb = FXCollections.observableArrayList();
        System.out.println(AutoPark.getInstance().getSubscribedVehicles());
        vecOb.addAll(AutoPark.getInstance().getSubscribedVehicles());
        return vecOb;
    }
    private ObservableList<ParkRecord> bringParkRecords() {
        ObservableList<ParkRecord > vecOb = FXCollections.observableArrayList();
        System.out.println(AutoPark.getInstance().getParkRecords());
        vecOb.addAll(AutoPark.getInstance().getParkRecords());
        return vecOb;
    }
    @FXML
    public void showVehicles(){
        col_Plate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        col_Begin.setCellValueFactory(new PropertyValueFactory<>("begin"));
        col_end.setCellValueFactory(new PropertyValueFactory<>("end"));
        table_subs.setItems(bringVehicle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Girdik");
        showParkRecords();
        showVehicles();
    }
@FXML
    private void showParkRecords() {
        col_Vehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle2"));
        col_start.setCellValueFactory(new PropertyValueFactory<>("enterTime"));
        col_endTime.setCellValueFactory(new PropertyValueFactory<>("exitTime"));
        table_records.setItems(bringParkRecords());
    }
@FXML
    public void close () {
        Stage stage = (Stage) table_subs.getScene().getWindow();
        stage.close();
}
    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            close();
        }
    }


}
