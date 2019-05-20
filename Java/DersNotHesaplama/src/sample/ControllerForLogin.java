package sample;

import Backend.Ders;
import Backend.Ogrenci;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerForLogin {

    @FXML
    private TextField enterHourlyFee;

    @FXML
    private JFXButton enterSystem;

    @FXML
    private Text QuestionsText;

    @FXML
    void enterTheSystem( ) {
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
    }



}
