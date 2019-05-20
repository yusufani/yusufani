package sample;

import Backend.Ders;
import Backend.NotBaremi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerForIsParked {

    @FXML
    private TextField enterDersName;

    @FXML
    private Text resultofPark;

    @FXML
    private TextField YuzdeFor1;

    @FXML
    private TextField PuanFor1;

    @FXML
    private TextField YuzdeFor2;

    @FXML
    private TextField PuanFor2;

    @FXML
    private TextField YuzdeFor3;

    @FXML
    private TextField PuanFor3;

    @FXML
    private TextField YuzdeFor4;

    @FXML
    private TextField PuanFor4;

    @FXML
    private TextField YuzdeFor5;

    @FXML
    private TextField
            PuanFor5;

    @FXML
    private Text resulText;
    @FXML
    private TextField notBaremiSayisi;


    @FXML
    private TextField sinir;

    @FXML
    void search(ActionEvent event) {
        Ders dersl = new Ders(enterDersName.getText());
        int sayi =Integer.parseInt(notBaremiSayisi.getText())-1;
        dersl.getNotBaremiArrayList().add(new NotBaremi(Double.parseDouble(YuzdeFor1.getText()),Double.parseDouble(PuanFor1.getText())));
        if(sayi>= 2)dersl.getNotBaremiArrayList().add(new NotBaremi(Double.parseDouble(YuzdeFor2.getText()),Double.parseDouble(PuanFor2.getText())));
        if(sayi>= 3)dersl.getNotBaremiArrayList().add(new NotBaremi(Double.parseDouble(YuzdeFor3.getText()),Double.parseDouble(PuanFor3.getText())));
        if(sayi>= 4)dersl.getNotBaremiArrayList().add(new NotBaremi(Double.parseDouble(YuzdeFor4.getText()),Double.parseDouble(PuanFor4.getText())));
        if(sayi>= 5)dersl.getNotBaremiArrayList().add(new NotBaremi(Double.parseDouble(YuzdeFor5.getText()),Double.parseDouble(PuanFor5.getText())));
        resulText.setVisible(true);
        resulText.setText("Notunuz " +dersl.siniraGoreNotHesapla(Integer.parseInt(sinir.getText())));
        }
    }

