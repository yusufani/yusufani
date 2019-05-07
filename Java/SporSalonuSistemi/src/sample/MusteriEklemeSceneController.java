package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.beans.VetoableChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MusteriEklemeSceneController implements Initializable {

    @FXML private JFXTextField ad;
    @FXML private JFXTextField soyad;
    @FXML private JFXTextField sifre;
    @FXML private JFXComboBox<String> cinsiyet;
    @FXML private JFXTextField kilo;
    @FXML private JFXTextField boy;
    @FXML private JFXTextField kasOrani;
    @FXML private JFXTextField yagOrani;
    @FXML private TableView musterilerTablo;
    VeriTabani x = VeriTabani.getInstance();
    private ArrayList<Musteri> musteriler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cinsiyet.getItems().addAll("Erkek","Kad?n");
        cinsiyet.setValue("Erkek");
        tabloGoruntule();
    }

    @FXML
    public void musteriEkle(){

        char c=0;
        if(cinsiyet.getValue().equals("Erkek")){
            c = 0;
        }
        else if(cinsiyet.getValue().equals("Kad?n")){
            c = 1;
        }
        Musteri musteri = new Musteri(ad.getText(),soyad.getText(),sifre.getText(),cinsiyet.getValue());
        musteri.setKutle(Double.parseDouble(kilo.getText()));
        musteri.setBoy(Integer.parseInt(boy.getText()));
        musteri.setKasOrani(Double.parseDouble(kasOrani.getText()));
        musteri.setYagOrani(Double.parseDouble(yagOrani.getText()));
        x.musteriEkle(musteri);
        musterilerTablo.getItems().add(musteri);
        this.ad.clear();
        this.soyad.clear();
        this.sifre.clear();
        this.kilo.clear();
        this.boy.clear();
        this.kasOrani.clear();
        this.yagOrani.clear();
    }

    public void tabloGoruntule(){
        musteriler = x.tumMusterileriGetir();

        TableColumn<Musteri,Integer> idT = new TableColumn<>("ID");
        TableColumn<Musteri,String> adT = new TableColumn<>("Ad");
        TableColumn<Musteri,String> soyadT = new TableColumn<>("Soyad");
        TableColumn<Musteri,String> sifreT = new TableColumn<>("?ifre");
        TableColumn<Musteri,String> cinsiyetT = new TableColumn<>("Cinsiyet");
        TableColumn<Musteri,String> kutleT = new TableColumn<>("K?tle(KG)");
        TableColumn<Musteri,String> boyT = new TableColumn<>("Boy(CM)");
        TableColumn<Musteri,String> kasOraniT = new TableColumn<>("Kas Oran? (%)");
        TableColumn<Musteri,String> yagOraniT = new TableColumn<>("Ya? Oran? (%)");


        idT.setCellValueFactory(new PropertyValueFactory<>("id"));
        adT.setCellValueFactory(new PropertyValueFactory<>("isim"));
        soyadT.setCellValueFactory(new PropertyValueFactory<>("soyisim"));
        sifreT.setCellValueFactory(new PropertyValueFactory<>("sifre"));
        cinsiyetT.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
        kutleT.setCellValueFactory(new PropertyValueFactory<>("kutle"));
        boyT.setCellValueFactory(new PropertyValueFactory<>("boy"));
        kasOraniT.setCellValueFactory(new PropertyValueFactory<>("kasOrani"));
        yagOraniT.setCellValueFactory(new PropertyValueFactory<>("yagOrani"));

        musterilerTablo.getColumns().addAll(idT,adT,soyadT,sifreT,cinsiyetT,kutleT,boyT,kasOraniT,yagOraniT);

        ObservableList<Musteri> observableList = FXCollections.observableArrayList(musteriler);
        musterilerTablo.setItems(observableList);

    }


}
