package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PersonelSceneController implements Initializable {

    @FXML private PieChart memnuniyetPie;
    @FXML private BarChart<String,Integer> aletDolulukChart;
    @FXML private Label personelName;
    @FXML private Label personelID;
    VeriTabani x = VeriTabani.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Personel personel = VeriTabani.ps;
        personelName.setText("Giri? Yapan Personel: " + personel.getIsim() + " " + personel.getSoyisim());
        personelID.setText("ID: " + personel.getId());
        memnuniyetGoster();
        aletDolulukGoster();
    }

    public void memnuniyetGoster(){
        double mem = x.ortalamaMemnuniyetDuzeyi();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Memnun",mem),
                        new PieChart.Data("Memnun De?il",5-mem)
                );
        memnuniyetPie.setData(pieChartData);
        System.out.println(mem);
    }

    public void aletDolulukGoster(){
        HashMap<String,Integer> aletler = x.aletdolulukOranlari(x.getSporIndisAlet());

        //aletDolulukChart.getXAxis().setLabel("Aletler");
        aletDolulukChart.getYAxis().setLabel("Doluluk");
        XYChart.Series seri = new XYChart.Series();
        for(String key: aletler.keySet()){
            System.out.println(key);
            System.out.println(aletler.get(key));
            if(!key.equals("null"))
            seri.getData().add(new XYChart.Data<>(key,aletler.get(key)));
        }
        aletDolulukChart.getData().add(seri);
    }

    @FXML
    public void musteriEkleEkrani(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MusteriEklemeScene.fxml"));
        Stage stage = new Stage();
        stage.setTitle("M??teri Ekleme");
        stage.setScene(new Scene(root));
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }



}
