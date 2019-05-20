package Backend;

import java.util.ArrayList;

public class Ogrenci {
    private String name ;
    private ArrayList<Ders> dersler;
private static Ogrenci x=null;

    public void setName(String name) {
        this.name = name;
    }

    public void setDersler(ArrayList<Ders> dersler) {
        this.dersler = dersler;
    }

    public static Ogrenci getInstance(){
    if (x== null){
        x=new Ogrenci();
    }
    return x;
}
public Ogrenci( ) {
        dersler=new ArrayList<>();
    }
    public Ogrenci(String name) {
        this.name = name;dersler=new ArrayList<>();
    }

    public ArrayList<Ders> getDersler() {
        return dersler;
    }
}
