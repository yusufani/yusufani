
import java.util.Random;

public class Musteri extends Person{
    private String [] hareketler;
    private int boy,memnuniyet;
    private int []dersProgrami;
    private double kasOrani,kutle,yagOrani;
    public void setDersProgrami(int[] dersProgrami) {
        this.dersProgrami = dersProgrami;
    }
    public int[] getDersProgrami() {
        return dersProgrami;
    }
    public Musteri(String isim, String soyisim, String sifre,String cinsiyet) {
        super( isim, soyisim, sifre,cinsiyet);
        memnuniyet=3; // Ortalama deger ile baslar herkes
    }
    public Musteri(){};
    public String[] getHareketler() {
        return hareketler;
    }
    public void setHareketler(String[] hareketler) {
        this.hareketler = hareketler;
    }
    public double getKasOrani() {
        return kasOrani;
    }
    public void setKasOrani(double kasOrani) {
        this.kasOrani = kasOrani;
    }

    public int getBoy() {
        return boy;
    }

    public void setBoy(int boy) {
        this.boy = boy;
        if (boy> 300) {
            System.out.println("3 Metreden fazla boy olamaz lutfen boyunuzu dogru giridginizden emin olunuz");
            this.boy=300;
        }
    }

    public int getMemnuniyet() {
        return memnuniyet;
    }

    public void setMemnuniyet(int memnuniyet) {
        this.memnuniyet = memnuniyet;
        if(memnuniyet>5){
            System.out.println("Memnuniyet [0-5] degerlendirmek araliklarinda degerlendirebilir.");
            this.memnuniyet=5;
        }
        if(memnuniyet<0){
            System.out.println("Memnuniyet [0-5] degerlendirmek araliklarinda degerlendirebilir.");
            this.memnuniyet=0;
        }
    }
    public double getKutle() {
        return kutle;
    }
    public void setKutle(double kutle) {
        this.kutle = kutle;
        if(kutle> 300){
            System.out.println("Kutle en fazla 300 KG olabilir.");
            this.kutle=300;
        }
        if(kutle<20){
            System.out.println("Kutle en az 20 olabilir.");
            this.kutle=20;
        }
    }

    public double getYagOrani() {
        return yagOrani;
    }

    public void setYagOrani(double yagOrani) {
        this.yagOrani = yagOrani;
        if(yagOrani>70){
            System.out.println("Gecersiz Yag Orani");
            this.yagOrani=70;
        }
        if(yagOrani<=0){
            System.out.println("Gecersiz Yag Orani");
            this.yagOrani=1;
        }
    }

    public String[] hareketProgramiOlustur(int gunSayisi){ //
        /*Input: Musterinin spor salonuna geleceği gun sayısı
        Output: Musterinin hareketlerini içeren String dizisi
        Bu fonksiyon Musteri icin otomatik olarak ders programi olusturur.
        NOT: Otomatik program oluştururken herhangi bir uzmandan yardim alinmamistir.Paremetreler degişikli gösterebilir.
        3 Farklı program tipi bulunmaktadır.
        1-> Full Body : Tum vucut bolumlerinin calistirildigi program tipidir.Yeni baslayanlar icin idealdir.
        2-> Bolgesel : Her gun 2 farklı vucut bolgesini daha izole hareketlerle calistiran program tipidir.
        3-> Kardiyo : Kardiyovasküler sistemi geliştirdiği için herkesin yapması gereken program tipidir.
        Musterinin Spor salonuna geldiği gun sayisina göre otomatik olarak programa ayarlamar yapilmaktadir.
        Hatırlatma: Return edilen hareketler dizisinin veritabanina yazilmasini saglayan fonksiyona parametre olarak gönderilmesi gerekir.
        NOT2: Random sayilari belirli veritabani indislerine gore atanmaktadır.Eğer yeni bir hareket ederse koda mudahale gerekir.
        */
        StringBuilder mesaj = new StringBuilder("Vucut kitle indexine ve kas oranınınıza göre size uygun olan program ");
        Random random = new Random();
        String []hareketler= new String[7]; // Hareketlerin tutuldugu String
        for (int i=0;i<7;i++) hareketler[i]="null;";
        if ( this.getKasOrani() == 0 || this.getYagOrani()== 0){
            return hareketler;
        }
        String kardiyo=hareketStringiniDondur(37,38,39,40,41,42);//Kardiyo Programı
        int index= random.nextInt(2)+1;
        int tmp;
        if(getCinsiyet().equals("Erkek")) tmp=0;
        else tmp=1;
        if ((getYagOrani() < 17+6*tmp) && getKasOrani() > (36-tmp*10 )  ){
            mesaj.append("bolgesel antrenman tipidir.");
            System.out.println(mesaj);
            System.out.println("Sizin için uygun antrenman programı su sekildedir");
           String bacakOmuz= hareketStringiniDondur(index,index+2,index+4,index+6,index+8,index+10);
           String gogusArkaKol= hareketStringiniDondur(index+12,index+12+2,index+12+4,index+12+6,index+12+8,index+12+10);// Gogus ile bacak indisleri arasındaki fark 12
            String sirtOnKol=hareketStringiniDondur(index+24,index+24+2,index+24+4,index+24+6,index+24+8,index+24+10);// Gogus ile bacak indisleri arasındaki fark 24
            if (gunSayisi== 4 ) {
                hareketler[0]=bacakOmuz;
                hareketler[2]=gogusArkaKol;
                hareketler[4]=sirtOnKol;
                hareketler[1+2*random.nextInt(3)]=kardiyo;
            }
            else if (gunSayisi == 1){
                hareketler[random.nextInt(7)]=kardiyo;
            }
            else if ( gunSayisi == 2){
                int tmp2;
                 tmp=random.nextInt(7);
                while(tmp== (tmp2=random.nextInt(7)));
                hareketler[tmp]=kardiyo;
                hareketler[tmp2]=kardiyo;
            }
            else if (gunSayisi == 5){
                hareketler[1]=bacakOmuz;
                hareketler[3]=gogusArkaKol;
                hareketler[5]=sirtOnKol;
                tmp =2*random.nextInt(4);
                int tmp2;
                hareketler[tmp]=kardiyo;
                while(tmp ==( tmp2=2*random.nextInt(4)));
                hareketler[tmp2]=kardiyo;
            }
            else if (gunSayisi == 3 ){
                hareketler[1]=bacakOmuz;
                hareketler[3]=gogusArkaKol;
                hareketler[5]=sirtOnKol;
            }
            else if (gunSayisi== 6){
                hareketler[1]=bacakOmuz;
                hareketler[3]=gogusArkaKol;
                hareketler[5]=sirtOnKol;
                hareketler[0]=gogusArkaKol;
                hareketler[2]=sirtOnKol;
                hareketler[4]=bacakOmuz;
            }

        }
        else {
            mesaj.append("tum vucut antrenman tipidir");
            String fullBody=hareketStringiniDondur(index,index+2,index+6,index+8,index+12,index+14);
            fullBody+=hareketStringiniDondur(index+18,index+20,index+24,index+26,index+30,index+32);
            if (gunSayisi == 4 ) {
                hareketler[2]=fullBody;
                hareketler[4]=fullBody;
                hareketler[6]=fullBody;
                hareketler[(2*random.nextInt(3))+1]=kardiyo;
            }
            else if (gunSayisi == 1){
                hareketler[random.nextInt(7)]=kardiyo;
            }
            else if ( gunSayisi == 2){

                tmp=random.nextInt(7);
                int tmp2;
                while(tmp== (tmp2=random.nextInt(7)));
                hareketler[tmp]=kardiyo;
                hareketler[tmp2]=kardiyo;
            }
            else if (gunSayisi == 5){
                hareketler[2]=fullBody;
                hareketler[4]=fullBody;
                hareketler[6]=fullBody;
                tmp =2*random.nextInt(3)+1;
                        int tmp2;
                hareketler[tmp]= kardiyo;
                while(tmp!=( tmp2=1+(2*random.nextInt(3))));
                hareketler[tmp2]=kardiyo;
            }
            else if ( gunSayisi == 3){

                hareketler[2]=fullBody;
                hareketler[4]=fullBody;
                hareketler[6]=fullBody;
            }
            else if  (gunSayisi== 6){
                hareketler[2]=fullBody;
                hareketler[4]=fullBody;
                hareketler[6]=fullBody;
                hareketler[0]=fullBody;
                hareketler[1]=kardiyo;
                hareketler[5]=kardiyo;
            }
        }
        return hareketler;
    }

    private String hareketStringiniDondur(int s, int s1, int s2, int s3, int s4, int s5) {
        return s+";"+s1+";"+s2+";"+s3+";"+s4+";"+s5+";";
    }

    @Override
    public String toString() {
        return "ID:"+ getId()+"Isim ve soyisim="+getIsim()+getSoyisim()+"Cinsiyet="+getCinsiyet()+"Kas orani="+getKasOrani()+"Yag orani="+getYagOrani();
    }
}
