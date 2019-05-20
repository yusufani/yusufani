package Backend;

public class NotBaremi {
    private double katsayi;
    private double not ;

    public NotBaremi(double katsayi, double not) {
        this.katsayi = katsayi;
        this.not = not;
    }

    public double puanHesapla(){
        return katsayi*not/100;
    }

    public double getKatsayi() {
        return katsayi;
    }
}
