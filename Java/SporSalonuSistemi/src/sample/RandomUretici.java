package sample;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RandomUretici {
	private String dosya_erkek_isim="erkekisim.txt";
	private String dosya_kadin_isim="kadinisim.txt";
	private String dosya_soyisim="soyisim.txt";
	private String dosya_hareketler="hareketler.txt";

	public int dosyaninSatirSayisi(File dosya) {
		int sayac=0;
		Scanner scanner;
		try {
			scanner = new Scanner(dosya);
			while (scanner.hasNextLine()) {
				sayac++;
				scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sayac;
	}
	public String randDosyaOkuyucu(String dosya_adi) {
		String isim="NULLL";
		File file=new File(dosya_adi);
		Scanner dosya;
		try {
			dosya = new Scanner(file);
			Random rand=new Random();//random nesnesi
			int random_indis = rand.nextInt(dosyaninSatirSayisi(file));//0<x<dosya_satir_sayisi olacak x üret
			int sayac=0;
			while(sayac<=random_indis && dosya.hasNextLine()) {
				isim=dosya.nextLine();
				sayac++;
			}
			dosya.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return isim;
	}

	public void randomIsim(Musteri kisi) {
		//rastgele cinsiyet üretilip erkek ya da kadın isimlerinin dosyasına ulaşılıyor.
		RandomUretici random=new RandomUretici();
		String cinsiyet=kisi.getCinsiyet();

		if(cinsiyet.equals("Erkek")) {
			kisi.setIsim(randDosyaOkuyucu(dosya_erkek_isim));
		}
		else if(cinsiyet.equals("Kadın")) {
			kisi.setIsim(randDosyaOkuyucu(dosya_kadin_isim));
		}
		else {
			System.out.println("RandomIsim metoduna hatali cinsiyet girildi !!");
		}
	}

	public void randomSoyisim(Musteri kisi) {
		kisi.setSoyisim(randDosyaOkuyucu(dosya_soyisim));
	}

	public String randomCinsiyet(Musteri kisi) {
		Random rand = new Random();
		int cinsiyet=rand.nextInt(2);
		if(cinsiyet== 0 ) kisi.setCinsiyet("Erkek");
		else kisi.setCinsiyet("Kadın");
		return kisi.getCinsiyet();
	}
	public void randomBoy(Musteri kisi) {
		Random rand = new Random();
		if(kisi.getCinsiyet().equals("Erkek")) {
			int boy = rand.nextInt(36);
			kisi.setBoy(boy+155);
		}
		else if(kisi.getCinsiyet().equals("Kadın")){
			int boy = rand.nextInt(36);
			kisi.setBoy(boy+150);
		}
		else {
			System.out.println("Boy hatasi");
		}
	}
	public void randomMemnuniyet(Musteri kisi) {
		Random rand = new Random();
		int memnuniyet = rand.nextInt(6);
		kisi.setMemnuniyet(memnuniyet);
	}
	public void randomKasOrani(Musteri kisi) {
		Random rand = new Random();
		if(kisi.getCinsiyet().equals("Erkek")) {
			double kas = rand.nextDouble();//32-40 arası erkekte
			kas=kas*8;
			kas= Math.round(kas* 10) / 10.0;
			kisi.setKasOrani(32+kas);
		}
		else if(kisi.getCinsiyet().equals("Kadın")) {
			double kas = rand.nextDouble();//23-31 arası kadinda
			kas=kas*8;
			kas= Math.round(kas* 10) / 10.0;
			kisi.setKasOrani(23+kas);
		}
		else {
			System.out.println("KasOrani hatasi");
		}
	}
	public void randomKutle(Musteri kisi) {
		Random rand = new Random();
		if(kisi.getCinsiyet().equals("Erkek")) {
			double kutle = rand.nextDouble();//50-120 arası erkekte tahmini
			kutle=kutle*70;
			kutle= Math.round(kutle* 10) / 10.0;
			kisi.setKutle(50+kutle);
		}
		else if(kisi.getCinsiyet().equals("Kadın")) {
			double kutle = rand.nextDouble();//40-90 arası kadinda tahmini
			kutle=kutle*50;
			kutle= Math.round(kutle* 10) / 10.0;
			kisi.setKutle(40+kutle);
		}
		else {
			System.out.println("Kutle hatasi");
		}
	}
	public void randomYagOrani(Musteri kisi) {
		Random rand = new Random();
		if(kisi.getCinsiyet().equals("Erkek")) {
			double yag = rand.nextDouble();//6-30 arası erkekte
			yag=yag*24;
			yag= Math.round(yag* 10) / 10.0;
			kisi.setYagOrani(6+yag);
		}
		else if(kisi.getCinsiyet().equals("Kadın")) {
			double yag = rand.nextDouble();//14-32 arası kadinda
			yag=yag*18;
			yag= Math.round(yag* 10) / 10.0;
			kisi.setYagOrani(14+yag);
		}
		else {
			System.out.println("KasOrani hatasi");
		}
	}

	public int randomGunUret() {
		Random rand = new Random();
		int gun_sayisi = rand.nextInt(6);
		return gun_sayisi+1;
	}
	public int randomSaatUret() {
		Random rand = new Random();
		int saat = rand.nextInt(4);
		return saat+1;
	}
	public int[] randomDersSaatleri() {
		RandomUretici uret=new RandomUretici();
		int [] hafta=new int[7];
		for(int i=0;i<7;i++) {
			hafta[i]=0;
		}
		int gun_sayisi=uret.randomGunUret();
		for(int i=0;i<gun_sayisi;i++) {
			hafta[i]=uret.randomSaatUret();System.out.println(i+" "+hafta[i]);
		}
		return hafta;
	}
	public void randomSifre(Musteri kisi) {
		Random rand=new Random();
		char[]sifre=new char[6];
		for(int i=0;i<6;i++) {
			int x=rand.nextInt(25);
			sifre[i]=(char)(x+97);
		}
		String sifreS=String.copyValueOf(sifre);
		kisi.setSifre(sifreS);
	}
	public void randomTumDegerler(Musteri kisi) {
		randomCinsiyet(kisi);
		randomIsim(kisi);
		randomSoyisim(kisi);
		randomBoy(kisi);
		randomMemnuniyet(kisi);
		randomKasOrani(kisi);
		randomKutle(kisi);
		randomYagOrani(kisi);
		randomSifre(kisi);
	}

	public void MusteriUret() {
		Musteri musteri=new Musteri();
		VeriTabani x = VeriTabani.getInstance();
		RandomUretici rand=new RandomUretici();
		int [] dizi=new int[7];

		rand.randomTumDegerler(musteri);
		x.musteriEkle(musteri);
		musteri.setId(x.musteriIDgetir());

		dizi=rand.randomDersSaatleri();
		x.musteriDersPrograminiGuncelle(musteri.getId(),dizi);
		int gun_sayisi=0;
		for(int i=0;i<7;i++) {
			if(dizi[i]!=0) {
				gun_sayisi++;
			}
		}
		x.musteriHareketleriGuncelle(musteri.getId(), musteri.hareketProgramiOlustur(gun_sayisi));
	}





}