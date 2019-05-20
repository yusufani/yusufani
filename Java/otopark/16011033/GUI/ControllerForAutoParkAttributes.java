
package GUI;

        import Otopark.AutoPark;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.control.TextField;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.KeyEvent;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;
        import javafx.event.ActionEvent;

        import java.net.URL;
        import java.util.ResourceBundle;

public class ControllerForAutoParkAttributes implements Initializable {
    @FXML
    private TextField enterInfo;

    @FXML
    private Text info;

    @FXML
    private Text resultText;

    @FXML
    public void set() {

        if(Controller.getIsSetCapacity()){ // case For set Capicity Button
            int gettedCapacity =Integer.parseInt(enterInfo.getText());
            if ( gettedCapacity >=  AutoPark.getInstance().getCurrentParkedCount() ){
                AutoPark.getInstance().setCapacity(gettedCapacity);
                Controller.getInstance().updateCapacity();
                resultText.setVisible(true);
                resultText.setStyle("-fx-fill: green; -fx-font-size: 16px;");
                resultText.setText("Capacity Succesfully Uptated");
            }else if (gettedCapacity < 0 ){
                resultText.setVisible(true);
                resultText.setStyle("-fx-fill: red; -fx-font-size: 16px;");
                resultText.setText(
                        "Capacity can not be less than 0 ");
            }else if (  gettedCapacity < AutoPark.getInstance().getCurrentParkedCount()){
                resultText.setVisible(true);
                resultText.setStyle("-fx-fill: red; -fx-font-size: 16px;");
                resultText.setText(
                        "Capacity can not be less than current parked vehicle count  ");

            }

        }
        else {// case For set Hourly Fee Button
            AutoPark.getInstance().setHourlyFee(Double.parseDouble(enterInfo.getText()));
            Controller.getInstance().updateHourlyFee();
            resultText.setStyle("-fx-fill: green; -fx-font-size: 16px;");
            resultText.setText("Capacity Succesfully Uptated");
        }
    }
    @FXML
    public void exit(  ActionEvent event ) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       resultText.setVisible(false);
        if(Controller.getIsSetCapacity()){ // case For set Capicity Button
            info.setText("Please enter the capacity ");
            enterInfo.setPromptText("Capacity");
        }
        else {// case For set Hourly Fee Button
            info.setText("Please enter the Houry Fee ");
            enterInfo.setPromptText("Hourly Fee");
        }
    }
    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            set();
        }
    }
}
