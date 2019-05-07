package sample;
public class Test {
    public static void main (String args[]) {
        VeriTabani x = new VeriTabani();
        x.VeriTabaniniAc();
       Musteri deneme = x.musteriyiBul(1,"123456");
        // a= x.musteriyiBul(1,"123456");
        //System.out.println(a.getId()+" "+a.getIsim()+" "+a.getHareketler1());
        /*a.dersProgramiOlustur(x);
        System.out.println(a.getId()+" "+a.getIsim()+" "+a.getHareketler1());*/
        int [][] a = x.salonDolulukHistgorami();
        ////////////////////////////////////
        int []gecicidersprog={2,1,0,3,4,3,3};
        x.musteriDersPrograminiGuncelle(2,gecicidersprog);
        String []geciciHareketler={"null;","1;1;1;1;1;","null;","4;5;6;7;8;","2;2;2;2;2;2;","null;","3;3;3;3;3;3;"};
        x.musteriHareketleriGuncelle(1,geciciHareketler);
        for (int i = 1; i <5 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println("\n");
        }
        System.out.println(x.aletdolulukOranlari(x.getSporIndisAlet()));
        ///////////////////////////////
       /* System.out.println("Buradayiz");
        Musteri Yusuf = x.musteriyiBul(1,"123456");
        Musteri aykutunistedigi = x.musteriyiBul(16011241,"gmgceh");
        System.out.println(aykutunistedigi.toString());
        Yusuf.setCinsiyet("Erkek");
        System.out.println(Yusuf.getCinsiyet());
        Musteri ipek = x.musteriyiBul(3,"123456");
        Musteri Aykut = x.musteriyiBul(2,"123456");
        Musteri Faz = x.musteriyiBul(4,"123456");
       if(Yusuf != null ) x.musteriHareketleriGuncelle(Yusuf.getId(),Yusuf.hareketProgramiOlustur(5));
        if(ipek != null ) x.musteriHareketleriGuncelle(ipek.getId(),ipek.hareketProgramiOlustur(6));
        if(Aykut != null ) x.musteriHareketleriGuncelle(Aykut.getId(),Aykut.hareketProgramiOlustur(4));
        if(Faz != null ) x.musteriHareketleriGuncelle(Faz.getId(),Faz.hareketProgramiOlustur(3));
        x.musteriMemnuniyetiGuncelle(1,1);
        x.musteriMemnuniyetiGuncelle(2,2);
        x.musteriMemnuniyetiGuncelle(3,3);
        x.musteriMemnuniyetiGuncelle(4,4);
        System.out.println(x.ortalamaMemnuniyetDuzeyi());
        /////////////////////
        //VERI TABANINDAN HAREKETLERI VE INDISLERI ALMA
        HashMap<Integer,String> y = new HashMap<>();
        y=x.getSporIndisHareket();
        System.out.println(y);
        ///////////////////
        //Personel bulma
        Personel xyz= x.personeliBul(1,"123456");
        System.out.println(xyz.toString());
        x.aletdolulukOranlari(x.getSporIndisAlet());
        System.out.println(x.aletdolulukOranlari(x.getSporIndisAlet()));
        //////////////////////////////////////////
        //Musteri yazma
        Musteri yeni = new Musteri("Hayri","Mehmet","123456","Erkek");
        yeni.setYagOrani(13.0);
        yeni.setKutle(78);
        yeni.setKasOrani(35);
        yeni.setBoy(180);
        yeni.setMemnuniyet(5);

        x.musteriEkle(yeni);
        System.out.println(yeni.getId());
        Yusuf.setIsim("Yusufadamdir");
        x.musteriyiGuncelle(Yusuf);
        x.musteriHareketleriGuncelle(yeni.getId(),yeni.hareketProgramiOlustur(5));
        int [] tmp2 = {1,0,4,2,2,4,2};
        x.musteriDersPrograminiGuncelle(yeni.getId(),tmp2);
        x.tumMusterileriGetir();
        System.out.println(x.musteriIDgetir());*/
        x.VeriTabaniniKapa();

    }

}
