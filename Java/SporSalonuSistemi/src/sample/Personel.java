package sample;
public class Personel extends Person {
    public Personel( String isim, String soyisim, String sifre, String cinsiyet) {
        super( isim, soyisim, sifre, cinsiyet);
    }
    public double ortalamaMemnuniyetDuzeyiGoruntule(VeriTabani x){
        return x.ortalamaMemnuniyetDuzeyi();
    }

    @Override
    public String toString() {
        return  getId()+"\t"+getIsim()+" "+getSoyisim()+"\t+"+getCinsiyet();
    }

}
