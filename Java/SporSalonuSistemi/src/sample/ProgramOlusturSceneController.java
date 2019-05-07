package sample;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.omg.PortableInterceptor.ACTIVE;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProgramOlusturSceneController implements Initializable {

    @FXML
    private GridPane dolulukTablosu;
    private VeriTabani x = VeriTabani.getInstance();
    private ArrayList<ToggleGroup> groups = new ArrayList<>();
    private int gunSayisi;
    private int[] gunSaatleri;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();

    }

    @FXML
    public void programGunleriniKaydet(ActionEvent event){
        gunSayisi=0;
        gunSaatleri = new int[7];
        Musteri musteri = VeriTabani.ms;
        for(int i=0;i<7;i++){
            if(groups.get(i).getSelectedToggle()!=null){
                gunSayisi++;
            }
        }
        System.out.println(gunSayisi);
        int j;
        for(int i =0;i<7;i++){
            if(groups.get(i).getSelectedToggle()==null){
                gunSaatleri[i] = 0;
            }
            else{
                j=1;
                for(Toggle toggle: groups.get(i).getToggles()){
                    if(toggle.isSelected()){
                        gunSaatleri[i] = j;
                    }
                    else{
                        j++;
                    }
                }
            }

        }

        musteri.setDersProgrami(gunSaatleri);

        for(int i=0;i<7;i++){
            System.out.println(gunSaatleri[i]);
        }

        musteri.setHareketler(musteri.hareketProgramiOlustur(gunSayisi));
        VeriTabani.ms = musteri;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getOnCloseRequest().handle(new WindowEvent(window,WindowEvent.WINDOW_CLOSE_REQUEST));
        window.close();
    }

    public void createTable(){
        int[][] doluluk = x.salonDolulukHistgorami();
        for(int i=1;i<8;i++) {
            ToggleGroup group1 = new ToggleGroup();
            JFXRadioButton radioButton = new JFXRadioButton();
            JFXRadioButton radioButton1 = new JFXRadioButton();
            JFXRadioButton radioButton2 = new JFXRadioButton();
            JFXRadioButton radioButton3 = new JFXRadioButton();
            radioButton.setToggleGroup(group1);
            radioButton.setText("Doluluk\nOran?: " + doluluk[1][i-1]);
            radioButton1.setToggleGroup(group1);
            radioButton1.setText("Doluluk\nOran?: " + doluluk[2][i-1]);
            radioButton2.setToggleGroup(group1);
            radioButton2.setText("Doluluk\nOran?: " + doluluk[3][i-1]);
            radioButton3.setToggleGroup(group1);
            radioButton3.setText("Doluluk\nOran?: " + doluluk[4][i-1]);
            dolulukTablosu.add(radioButton, i, 1);
            dolulukTablosu.add(radioButton1, i, 2);
            dolulukTablosu.add(radioButton2, i, 3);
            dolulukTablosu.add(radioButton3, i, 4);
            groups.add(group1);
        }

    }



}

