public class Antrenor   {
    String isim,soyisim;
    int [] gunler;
    public Antrenor(String isim , String soyisim) {
        gunler=new int[7];
        this.isim=isim;
        this.soyisim=soyisim;
    }

    public int[] getGunler() {
        return gunler;
    }

    public void setGunler(int[] gunler) {
        this.gunler = gunler;
    }
}
