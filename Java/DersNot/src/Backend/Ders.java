package Backend;

import java.util.ArrayList;

public class Ders {
    private String name ;
    private ArrayList<NotBaremi> notBaremiArrayList ;

    public Ders(String name) {
        this.name = name;
        notBaremiArrayList=new ArrayList<>();
    }

    public ArrayList<NotBaremi> getNotBaremiArrayList() {
        return notBaremiArrayList;
    }
    public double siniraGoreNotHesapla(int sinir ){
        double puan=0;
        double yuzdelik=0;
        for( NotBaremi i : notBaremiArrayList){
            puan+=i.puanHesapla();
            yuzdelik+=i.getKatsayi();
        }
        yuzdelik=100-yuzdelik;
        puan =sinir-puan; // Adamın alması gerekn puan.
        return (100*puan/yuzdelik);
    }
}
