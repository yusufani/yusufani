package sample;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class MusteriSceneController implements Initializable {



    @FXML private TextField kilo;
    @FXML private TextField boy;
    @FXML private Label mesaj;
    @FXML private TableView programTablo;
    @FXML private JFXSlider memnuniyet;
    @FXML private JFXHamburger hamburger;
    @FXML private Label musteriIsim;
    private VeriTabani x = VeriTabani.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Musteri musteri = VeriTabani.ms;
        musteriIsim.setText(musteri.getIsim() + "\n" + musteri.getSoyisim());
        tabloGuncelle();

    }

    public void programOlusturEkrani (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProgramOlusturScene.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Program Olu?turma");
        stage.setScene(new Scene(root));
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnCloseRequest(e->tabloGuncelle());
    }

    @FXML
    public void kiloGuncelle(){
        Musteri musteri = VeriTabani.ms;
        double yeniKilo = Double.parseDouble(this.kilo.getText());
        musteri.setKutle(yeniKilo);
        x.musteriyiGuncelle(musteri);
        mesaj.setText("Kilonuz G?ncellendi.");
        VeriTabani.ms = musteri;
    }
    @FXML
    public void boyGuncelle(){
        Musteri musteri = VeriTabani.ms;
        int yeniBoy = Integer.parseInt(this.boy.getText());
        musteri.setBoy(yeniBoy);
        x.musteriyiGuncelle(musteri);
        mesaj.setText("Boyunuz G?ncellendi.");
        VeriTabani.ms = musteri;
    }

    @FXML
    public void VKIHesapla(){
        Musteri musteri = VeriTabani.ms;

        if(!kilo.getText().equals("") && !boy.getText().equals("")) {
            double yeniKilo = Double.parseDouble(kilo.getText());
            double yeniBoy = Integer.parseInt(boy.getText());
            yeniBoy /= 100;
            double vki = yeniKilo / Math.pow(yeniBoy, 2);
            DecimalFormat df = new DecimalFormat("#.##");
            mesaj.setText("Hesaplanan V?cut Kitle ?ndeksi : " + df.format(vki));
        }
        else{
            mesaj.setText("Kilo ve Boyunuzu giriniz.");
        }
        //musteri.setKutle(yeniKilo);
        //musteri.setBoy(yeniBoy);
        //x.musteriyiGuncelle(musteri);
    }

    @FXML
    public void memnuniyetGuncelle(){
        System.out.println("asd");
        Musteri musteri = VeriTabani.ms;
        int mem =  (int)memnuniyet.getValue();
        musteri.setMemnuniyet(mem);
        x.musteriMemnuniyetiGuncelle(musteri.getId(),mem);
        VeriTabani.ms = musteri;
    }


    public void tabloGuncelle(){


        Musteri musteri = VeriTabani.ms;

        int[] gunSaatleri = musteri.getDersProgrami();


        String[] saatler = new String[5];
        saatler[0] = "Bo? G?n";
        saatler[1] = "07.00-11.00";
        saatler[2] = "11.00-15.00";
        saatler[3] = "15.00-19.00";
        saatler[4] = "19.00-23.00";

        String[] gunler = new String[7];
        for(int i=0;i<7;i++){
            if(gunSaatleri[i]==0){
                gunler[i] = saatler[0];
            }
            else if(gunSaatleri[i]==1){
                gunler[i] = saatler[1];
            }
            else if(gunSaatleri[i]==2){
                gunler[i] = saatler[2];
            }
            else if(gunSaatleri[i]==3){
                gunler[i] = saatler[3];
            }
            else if(gunSaatleri[i]==4){
                gunler[i] = saatler[4];
            }
        }

        programTablo.getItems().clear();
        programTablo.getColumns().clear();

        TableColumn<Hareket,String> column1 = new TableColumn<>("Pazartesi\n" + gunler[0]);
        TableColumn<Hareket,String> column2 = new TableColumn<>("Sal?\n" + gunler[1]);
        TableColumn<Hareket,String> column3 = new TableColumn<>("?ar?amba\n" + gunler[2]);
        TableColumn<Hareket,String> column4 = new TableColumn<>("Per?embe\n" + gunler[3]);
        TableColumn<Hareket,String> column5 = new TableColumn<>("Cuma\n" + gunler[4]);
        TableColumn<Hareket,String> column6 = new TableColumn<>("Cumartesi\n" + gunler[5]);
        TableColumn<Hareket,String> column7 = new TableColumn<>("Pazar\n" + gunler[6]);

        column1.setCellValueFactory(new PropertyValueFactory<>("pazartesi"));
        column2.setCellValueFactory(new PropertyValueFactory<>("sali"));
        column3.setCellValueFactory(new PropertyValueFactory<>("carsamba"));
        column4.setCellValueFactory(new PropertyValueFactory<>("persembe"));
        column5.setCellValueFactory(new PropertyValueFactory<>("cuma"));
        column6.setCellValueFactory(new PropertyValueFactory<>("cumartesi"));
        column7.setCellValueFactory(new PropertyValueFactory<>("pazar"));


        programTablo.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7);

        HashMap<Integer,String> haret = x.getSporIndisHareket();

        String[] h = musteri.getHareketler();

        String[] h1 = new String[7];
        for(int i=0;i<7;i++){
            h1[i] = "null;";
        }

        /*for(int i =0;i<7;i++){
            if(gunSaatleri[i] != 0){
                for(int j=i;j<7;j++){
                    if(!h[j].equals("null;")){
                        String tmp = h[j];
                        h[j] = h[i];
                        h[i] = tmp;
                        break;
                    }
                }
            }
        }*/
        int j=0;
        for(int i =0;i<7;i++){
            if(gunSaatleri[i]!=0){
                while(h[j].equals("null;")){
                    j++;
                }
                h1[i] = h[j];
                j++;
            }
        }



        ArrayList<Integer[]> arrays = new ArrayList<>();
        int k =0;
        j =0;
        for(int i=0;i<7;i++){
            arrays.add(new Integer[6]);
            if (!h1[i].equals("null;")){

                String[] sayilar = h1[i].split(";");
                /*for(int a = 0;a<6;a++){
                    //arrays.get(i)[a] = Integer.parseInt(sayilar[a]);
                    System.out.println(sayilar[a]);
                }*/
                k=0;
                for(String a : sayilar){
                    //System.out.println(a);
                    if(!a.equals("null")){
                        arrays.get(i)[k] = Integer.parseInt(a);
                        System.out.println(arrays.get(i)[k]);
                        k++;
                    }

                }
            }

        }


        programTablo.setItems(hareketleriAl(haret,arrays));
        x.musteriDersPrograminiGuncelle(musteri.getId(),gunSaatleri);
        x.musteriHareketleriGuncelle(musteri.getId(),h1);


    }

    public ObservableList<Hareket> hareketleriAl(HashMap<Integer,String> haret,ArrayList<Integer[]> arrays){
        ObservableList<Hareket> harekets = FXCollections.observableArrayList();
        String[] ha = new String[7];
        for(int k=0;k<6;k++){
            for(int i=0;i<7;i++){
                ha[i] = haret.get(arrays.get(i)[k]);
            }
            harekets.add(new Hareket(ha[0],ha[1],ha[2],ha[3],ha[4],ha[5],ha[6]));
        }

        return harekets;
    }

}
