package GUI;

import Otopark.AutoPark;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForIsParked implements Initializable {

    @FXML
    private TextField enterPlate;
    @FXML
    private Text resultofPark;
    @FXML
    public void search() {
        boolean sonuc = AutoPark.getInstance().isParked(enterPlate.getText().toUpperCase());
        resultofPark.setVisible(true);
        if (sonuc == true) {
            resultofPark.setStyle("-fx-fill: green; -fx-font-size: 16px;");
            resultofPark.setText("Vehicle is Parked ");
        } else {
            resultofPark.setStyle("-fx-fill: red; -fx-font-size: 16px;");
            resultofPark.setText("Vehicle is not Parked ");

        }
    }
    @FXML
    void exit( ) {
        Stage stage =(Stage) enterPlate.getScene().getWindow();
        stage.close();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultofPark.setVisible(false);
    }
    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }
}
