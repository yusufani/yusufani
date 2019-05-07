package sample;

import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VeriTabani {
	public static final String DB_NAME = "musteri.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:"+ DB_NAME;
	public static final String TABLE_MUSTERI_GENELBILGILER="Musteri_Genel_Bilgileri";
	public static final String TABLE_MUSTERI_HAREKETLER="Musteri_Hareketleri";
	public static final String TABLE_MUSTERI_DERSPROGRAMI="Musteri_Gun_ve_Saatleri";
	public static final String TABLE_HAREKET_VE_ALETLER ="Spor_Hareketleri_ve_Aletleri";
	public static final String TABLE_PERSONEL_GENEL_BILGILER="Personel_Genel_Bilgileri";
    public static final String SUTUN_PERSONEL_GENELBILGILER_ID = "personelID";
    public static final String SUTUN_PERSONEL_GEBELBILGILER_ADI = "Name";
    public static final String SUTUN_PERSONEL_GENELBILGILER_SOYADI= "Surname";
    public static final String SUTUN_PERSONEL_GENELBILGILER_SIFRESI ="Password";
    public static final String SUTUN_PERSONEL_GENELBILGILER_CINSIYETI ="Cinsiyet";
	public static final String SUTUN_MUSTERI_GENELBILGILER_ID = "customerID";
	public static final String SUTUN_MUSTERI_GEBELBILGILER_ADI = "Name";
	public static final String SUTUN_MUSTERI_GENELBILGILER_SOYADI= "Surname";
	public static final String SUTUN_MUSTERI_GENELBILGILER_SIFRESI ="Password";
	public static final String SUTUN_MUSTERI_GENELBILGILER_CINSIYETI ="Cinsiyet";
	public static final String SUTUN_MUSTERI_GENELBILGILER_KUTLE= "Kutle";
	public static final String SUTUN_MUSTERI_GENELBILGILER_BOY= "Boy";
	public static final String SUTUN_MUSTERI_GENELBILGILER_KASORANI= "KasOrani";
	public static final String SUTUN_MUSTERI_GENELBILGILER_YAGORANI= "YagOrani";
	public static final String SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET="Memnuniyet";
	public static final String SUTUN_MUSTERI_HAREKETLER_ID ="customerID";
	public static final String SUTUN_MUSTERI_HAREKETLER_PAZARTESI = "HareketPazartesi";
	public static final String SUTUN_MUSTERI_HAREKETLER_SALI = "HareketSali";
	public static final String SUTUN_MUSTERI_HAREKETLER_CARSAMBA = "HareketCarsamba";
	public static final String SUTUN_MUSTERI_HAREKETLER_PERSEMBE = "HareketPersembe";
	public static final String SUTUN_MUSTERI_HAREKETLER_CUMA = "HareketCuma";
	public static final String SUTUN_MUSTERI_HAREKETLER_CUMARTESI = "HareketCumartesi";
	public static final String SUTUN_MUSTERI_HAREKETLER_PAZAR = "HareketPazar";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_ID ="customerID";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_PAZARTESI = "Pazartesi";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_SALI = "Sali";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_CARSAMBA = "Carsamba";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_PERSEMBE = "Persembe";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_CUMA = "Cuma";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_CUMARTESI = "Cumartesi";
	public static final String SUTUN_MUSTERI_DERSPROGRAMI_PAZAR = "Pazar";
    public static final String SUTUN_HAREKET_VE_ALETLER_INDIS = "Hareket_�ndisi";
    public static final String SUTUN_HAREKET_VE_ALETLER_HAREKET_ADI = "Hareket_Adi";
    public static final String SUTUN_HAREKET_VE_ALETLER_CALISAN_BOLGE = "Calistirilan_Bolge";
    public static final String SUTUN_HAREKET_VE_ALETLER_ALET_ADI = "Alet";
	public Connection baglanti;
	private static VeriTabani single_instance = null;
	public static VeriTabani getInstance(){
		if (single_instance == null)
			single_instance = new VeriTabani();

		return single_instance;
	}
	public boolean VeriTabaniniAc() {
		try {
			baglanti=DriverManager.getConnection(CONNECTION_STRING);
			return true;
		}catch(SQLException e) {
			System.out.println("VeriTabanina Baglanilamadi");
			return false;
		}
	}
	public void VeriTabaniniKapa() {
		try {
			if( baglanti != null) {
				baglanti.close();
				System.out.println("Veritabani Basariyla Kapatildi");
			}
		}catch ( SQLException e ) {
			e.printStackTrace();
			System.out.println("Veritabani kapatilamadi");
		}
	}
	public Musteri musteriyiBul(int customerID,String password) {
		/*Bu fonksiyon ID'si ve Sifresi verilen Musteriyi bulup Musteri class�ndan
		bir objesinin alanlar�n� dondurerek return eder.*/
		boolean flag = true;
		StringBuilder sb = new StringBuilder("SELECT * FROM "); //�lgili eleman veri tabanindan cekiliyor.
		sb.append(TABLE_MUSTERI_GENELBILGILER+" "+"INNER JOIN "+TABLE_MUSTERI_HAREKETLER+","+TABLE_MUSTERI_DERSPROGRAMI+" ON "+TABLE_MUSTERI_GENELBILGILER+"."+SUTUN_MUSTERI_GENELBILGILER_ID+"="+TABLE_MUSTERI_HAREKETLER+"."+ SUTUN_MUSTERI_HAREKETLER_ID +"="+TABLE_MUSTERI_DERSPROGRAMI+"."+ SUTUN_MUSTERI_DERSPROGRAMI_ID);
		sb.append(" WHERE "+TABLE_MUSTERI_GENELBILGILER+"."+SUTUN_MUSTERI_GENELBILGILER_ID+" = "+customerID+ " AND "+TABLE_MUSTERI_GENELBILGILER+"."+SUTUN_MUSTERI_GENELBILGILER_SIFRESI+" = '"+password+"'");
		try (Statement statement = baglanti.createStatement();
			 ResultSet sonuc = statement.executeQuery(sb.toString())) {
			if(sonuc.next()) {
				Musteri musteri = new Musteri( sonuc.getString(SUTUN_MUSTERI_GEBELBILGILER_ADI), sonuc.getString(SUTUN_MUSTERI_GENELBILGILER_SOYADI), sonuc.getString(SUTUN_MUSTERI_GENELBILGILER_SIFRESI),sonuc.getString(SUTUN_MUSTERI_GENELBILGILER_CINSIYETI));
				musteri.setId(sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_ID));
				musteri.setBoy((char) sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_BOY));
				musteri.setKasOrani(sonuc.getDouble(SUTUN_MUSTERI_GENELBILGILER_KASORANI));
				musteri.setKutle(sonuc.getDouble(SUTUN_MUSTERI_GENELBILGILER_KUTLE));
				musteri.setMemnuniyet((char) sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET));
				musteri.setYagOrani(sonuc.getDouble(SUTUN_MUSTERI_GENELBILGILER_YAGORANI));
				String[] hareketler = new String[7];
				hareketler[0] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_PAZARTESI);
				hareketler[1] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_SALI);
				hareketler[2] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_CARSAMBA);
				hareketler[3] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_PERSEMBE);
				hareketler[4] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_CUMA);
				hareketler[5] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_CUMARTESI);
				hareketler[6] = sonuc.getString(SUTUN_MUSTERI_HAREKETLER_PAZAR);
				musteri.setHareketler(hareketler);
				int[] dersProgrami = new int[7];
				dersProgrami[0] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_PAZARTESI);
				dersProgrami[1] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_SALI);
				dersProgrami[2] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_CARSAMBA);
				dersProgrami[3] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_PERSEMBE);
				dersProgrami[4] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_CUMA);
				dersProgrami[5] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_CUMARTESI);
				dersProgrami[6] = sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_PAZAR);
				musteri.setDersProgrami(dersProgrami);
				return musteri;
			}
			else{
				System.out.println("Boyle bir musteri bulunamadi"); // Exceptioni ayarla
				return null;
			}
		} catch (SQLException e){
				System.out.println("Sorgu Basarisiz");
				e.printStackTrace();
				return null;
			}
	}
	public void musteriDersPrograminiGuncelle( int customerID, int []dersProgrami){
		/* Bu fonksiyon ID'si verilen musterinin verilen dizideki ders programini veritabaninda gunceller*/
		StringBuilder sorgu = new StringBuilder( "UPDATE " + TABLE_MUSTERI_DERSPROGRAMI + " SET ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_PAZARTESI +" = "+dersProgrami[0]+", ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_SALI +" = "+dersProgrami[1]+", ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_CARSAMBA +" = "+dersProgrami[2]+", ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_PERSEMBE +" = "+dersProgrami[3]+", ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_CUMA +" = "+dersProgrami[4]+", ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_CUMARTESI +" = "+dersProgrami[5]+", ");
		sorgu.append(SUTUN_MUSTERI_DERSPROGRAMI_PAZAR +" = "+dersProgrami[6]+" ");
		sorgu.append("WHERE "+ SUTUN_MUSTERI_DERSPROGRAMI_ID +"="+customerID);
		try(Statement statement = baglanti.createStatement()){
			statement.executeUpdate(sorgu.toString());
			System.out.println("Bilgiler Guncellendi");
		}catch (SQLException e){
			System.out.println("Bilgiler Guncellennirken bir sorunla karsilasildi.");
			e.printStackTrace();
		}
	}
	public void musteriHareketleriGuncelle( int customerID, String []hareketler){
		/* Bu fonksiyon ID'si verilen musterinin verilen dizideki haraketlerini veritabaninda gunceller*/
		StringBuilder sorgu = new StringBuilder( "UPDATE " + TABLE_MUSTERI_HAREKETLER + " SET ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_PAZARTESI +" = '"+hareketler[0]+"' , ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_SALI +" = '"+hareketler[1]+"' , ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_CARSAMBA +" = '"+hareketler[2]+"' , ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_PERSEMBE +" = '"+hareketler[3]+"' , ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_CUMA +" = '"+hareketler[4]+"' , ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_CUMARTESI +" = '"+hareketler[5]+"' , ");
		sorgu.append(SUTUN_MUSTERI_HAREKETLER_PAZAR +" = '"+hareketler[6]+"' ");
		sorgu.append("WHERE "+ SUTUN_MUSTERI_HAREKETLER_ID +" = "+customerID);
		try(Statement statement = baglanti.createStatement()
			){
			statement.executeUpdate(sorgu.toString());
			System.out.println("Bilgiler Guncellendi");
		}catch (SQLException e){
			System.out.println("Bilgiler Guncellennirken bir sorunla karsilasildi.");
			e.printStackTrace();
		}
	}
	public int[][] salonDolulukHistgorami(){
		/* BU fonksiyon salonun g�ne ve saate g�re doluluk oranlar�n� tutan histogram�n� bulur ve return eder
		Not: Kullan�m kolayl��� olmas� i�in histogramda ilk sat�r �nemsiz veriyi kapsar.
		Saat aral�klar�:
		07.00-11.00 -> histogram[1][x] indisine
		11.00-15.00 -> histogram[2][x] indisine
		15.00-19.00 -> histogram[3][x] indisine
		19.00-23.00 -> histogram[4][x] indisine denk gelmektedir.
		 */
		int [][] histogram= new int[5][7];
		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(TABLE_MUSTERI_DERSPROGRAMI);
		try(Statement statement = baglanti.createStatement();
			ResultSet sonuc = statement.executeQuery(sb.toString())){
			while(sonuc.next()) {
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_PAZARTESI)][0]++;
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_SALI)][1]++;
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_CARSAMBA)][2]++;
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_PERSEMBE)][3]++;
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_CUMA)][4]++;
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_CUMARTESI)][5]++;
				histogram[sonuc.getInt(SUTUN_MUSTERI_DERSPROGRAMI_PAZAR)][6]++;
			}
			return histogram;
		}
		catch (SQLException e) {
			System.out.println("Veri Tabanindan saat cekilirken bir hata olustu "+ e.getMessage());
			return null;
		}
	}
	public void musteriMemnuniyetiGuncelle(int customerID, int memnuniyet){
	String sorgu ="UPDATE "+TABLE_MUSTERI_GENELBILGILER+" SET "+SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET+" ="+memnuniyet +" WHERE "+SUTUN_MUSTERI_GENELBILGILER_ID+" = "+customerID;
			try(Statement statement = baglanti.createStatement()){
				statement.executeUpdate(sorgu);
	}
	catch (SQLException e){
		System.out.println("Memnuniyet bilgisi guncellenemedi");
		e.printStackTrace();
	}
	}
	public double ortalamaMemnuniyetDuzeyi(){
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET+" FROM ");
		sb.append(TABLE_MUSTERI_GENELBILGILER);
		try(Statement statement = baglanti.createStatement();
			ResultSet sonuc=statement.executeQuery(sb.toString())){
				int i=0;
				double toplam=0.0;
				while(sonuc.next()){
						toplam+=sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET);
						i++;
					}
				if(i!= 0){
					toplam/=i;
				}
				return toplam;
		}
		catch (SQLException e){
			System.out.println("Ortalama Memnuniyet hesaplan�rken bir sorun ortaya cikti");
			e.printStackTrace();
			return 0.0;
		}
	}
    public HashMap<Integer, String> getSporIndisHareket(){
    StringBuilder sorgu = new StringBuilder("SELECT ");
    sorgu.append(SUTUN_HAREKET_VE_ALETLER_INDIS+","+SUTUN_HAREKET_VE_ALETLER_HAREKET_ADI);
    sorgu.append(" FROM "+TABLE_HAREKET_VE_ALETLER);
    HashMap<Integer,String> hareketler = new HashMap<>();
    try(Statement statement = baglanti.createStatement();
        ResultSet sonuc = statement.executeQuery(sorgu.toString())){
        while (sonuc.next()){
            hareketler.put(sonuc.getInt(SUTUN_HAREKET_VE_ALETLER_INDIS),sonuc.getString(SUTUN_HAREKET_VE_ALETLER_HAREKET_ADI));
        }
    }catch (SQLException e){
        System.out.println("Spor Hareketleri ve indisleri al�n�rken bir hata meydana geldi");
        e.printStackTrace();
    }
    return hareketler;
    }
    public HashMap<Integer, String> getSporIndisAlet(){
        StringBuilder sorgu = new StringBuilder("SELECT ");
        sorgu.append(SUTUN_HAREKET_VE_ALETLER_INDIS+","+SUTUN_HAREKET_VE_ALETLER_ALET_ADI);
        sorgu.append(" FROM "+TABLE_HAREKET_VE_ALETLER);
        HashMap<Integer,String> hareketler = new HashMap<>();
        try(Statement statement = baglanti.createStatement();
            ResultSet sonuc = statement.executeQuery(sorgu.toString())){
            while (sonuc.next()){
                hareketler.put(sonuc.getInt(SUTUN_HAREKET_VE_ALETLER_INDIS),sonuc.getString(SUTUN_HAREKET_VE_ALETLER_ALET_ADI));
            }
        }catch (SQLException e){
            System.out.println("Spor Hareketleri indisleri ve aletleri al�n�rken bir hata meydana geldi");
            e.printStackTrace();
        }
        return hareketler;
    }
    public Personel personeliBul(int personelID,String password) {
		/*Bu fonksiyon ID'si ve Sifresi verilen Musteriyi bulup Musteri class�ndan
		bir objesinin alanlar�n� dondurerek return eder.*/
        boolean flag = true;
        StringBuilder sb = new StringBuilder("SELECT * FROM "); //�lgili eleman veri tabanindan cekiliyor.
        sb.append(TABLE_PERSONEL_GENEL_BILGILER);
        sb.append(" WHERE "+TABLE_PERSONEL_GENEL_BILGILER+"."+SUTUN_PERSONEL_GENELBILGILER_ID+" = "+personelID+ " AND "+TABLE_PERSONEL_GENEL_BILGILER+"."+SUTUN_PERSONEL_GENELBILGILER_SIFRESI+" = "+password);
        try (Statement statement = baglanti.createStatement();
             ResultSet sonuc = statement.executeQuery(sb.toString())) {
            if(sonuc.next()) {
                Personel personel = new Personel( sonuc.getString(SUTUN_PERSONEL_GEBELBILGILER_ADI), sonuc.getString(SUTUN_PERSONEL_GENELBILGILER_SOYADI), sonuc.getString(SUTUN_PERSONEL_GENELBILGILER_SIFRESI), sonuc.getString(SUTUN_PERSONEL_GENELBILGILER_CINSIYETI));
                personel.setId(sonuc.getInt(SUTUN_PERSONEL_GENELBILGILER_ID));
                return personel;
            }
            else{
                System.out.println("Boyle bir personel bulunamadi"); // Exceptioni ayarla
                return null;
            }
        } catch (SQLException e){
            System.out.println("Sorgu Basarisiz");
            e.printStackTrace();
            return null;
        }
    }
    public HashMap<String,Integer> aletdolulukOranlari(HashMap<Integer,String> hareketeKarsilikGelenAlet){
	    HashMap<String,Integer> aletler = new HashMap<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM "); //�lgili eleman veri tabanindan cekiliyor.
        sb.append(TABLE_MUSTERI_HAREKETLER);
        try (Statement statement = baglanti.createStatement();
             ResultSet sonuc = statement.executeQuery(sb.toString())) {
            while(sonuc.next()) {
                String []dizi = null;
                String tmp;
                tmp=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_PAZARTESI);
                tmp+=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_SALI);
                tmp+=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_CARSAMBA);
                tmp+=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_PERSEMBE);
                tmp+=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_CUMA);
                tmp+=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_CUMARTESI);
                tmp+=sonuc.getString(SUTUN_MUSTERI_HAREKETLER_PAZAR);
                dizi=tmp.split(";");
                if(dizi.length != 1){
                    for(int i=0;i<dizi.length;i++){
                        if(!(dizi[i].equals("null"))) {
                            tmp = hareketeKarsilikGelenAlet.get(Integer.parseInt(dizi[i]));
                            if (aletler.containsKey(tmp)) {
                                aletler.put(tmp, aletler.get(tmp) + 1);
                            } else {
                                aletler.put(tmp, 1);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Sorgu Basarisiz");
            e.printStackTrace();
            return null;
        }

        return aletler;
    }
    public boolean musteriEkle(Musteri musteri) {
		String sorgu = "INSERT INTO " + TABLE_MUSTERI_GENELBILGILER + " ( '" + SUTUN_MUSTERI_GEBELBILGILER_ADI + "','" + SUTUN_MUSTERI_GENELBILGILER_SOYADI + "','";
		sorgu += SUTUN_MUSTERI_GENELBILGILER_SIFRESI + "','" + SUTUN_MUSTERI_GENELBILGILER_CINSIYETI + "','" + SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET + "','";
		sorgu += SUTUN_MUSTERI_GENELBILGILER_YAGORANI + "','" + SUTUN_MUSTERI_GENELBILGILER_KASORANI + "','" + SUTUN_MUSTERI_GENELBILGILER_KUTLE + "','" + SUTUN_MUSTERI_GENELBILGILER_BOY + "')";
		sorgu += " VALUES ('" + musteri.getIsim() + "','" + musteri.getSoyisim() + "','" + musteri.getSifre() + "','" + musteri.getCinsiyet() + "'," + musteri.getMemnuniyet() + ",";
		sorgu += musteri.getYagOrani() + "," + musteri.getKasOrani() + "," + musteri.getKutle() + "," + musteri.getBoy() + ");";
		String sorgu2 = "INSERT INTO " + TABLE_MUSTERI_DERSPROGRAMI + " ('" + SUTUN_MUSTERI_DERSPROGRAMI_ID + "') SELECT seq FROM sqlite_sequence WHERE name =\"" + TABLE_MUSTERI_GENELBILGILER + "\";";
		String sorgu3 = "INSERT INTO " + TABLE_MUSTERI_HAREKETLER + " ('" + SUTUN_MUSTERI_HAREKETLER_ID + "') SELECT seq FROM sqlite_sequence WHERE name =\"" + TABLE_MUSTERI_GENELBILGILER + "\";";
		String sorgu4 = "SELECT seq from sqlite_sequence WHERE name = '" + TABLE_MUSTERI_GENELBILGILER + "'";
		System.out.println("-"+musteri.getSifre()+"-");
		System.out.println(sorgu);
		try (Statement statement = baglanti.createStatement()
		) {
			statement.addBatch(sorgu);
			System.out.println("Hata");
			statement.addBatch(sorgu2);
			System.out.println("Hata2");
			statement.addBatch(sorgu3);
			System.out.println("Hata3");
			statement.executeBatch();
			System.out.println("Hata4");
			ResultSet sonuc = statement.executeQuery(sorgu4);
			System.out.println("Hata5");
			if (sonuc.next()) musteri.setId(sonuc.getInt("seq"));
			System.out.println("Hata6");
			return true;
		} catch (SQLException e) {
			System.out.println("Musteri eklenirken hata meydana geldi");
			e.printStackTrace();
			return false;
		}
	}
public boolean musteriyiGuncelle(Musteri musteri){
	/* Bu fonksiyon ID'si verilen musterinin verilen dizideki haraketlerini veritabaninda gunceller*/
	StringBuilder sorgu = new StringBuilder( "UPDATE " + TABLE_MUSTERI_GENELBILGILER + " SET ");
	sorgu.append(SUTUN_MUSTERI_GEBELBILGILER_ADI +" = '"+musteri.getIsim()+"' , ");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_SOYADI +" = '"+musteri.getSoyisim()+"' , ");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_SIFRESI +" = '"+musteri.getSifre()+"' , ");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_KASORANI +" = '"+musteri.getKasOrani()+"' , ");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_CINSIYETI +" = '"+musteri.getCinsiyet()+"' , ");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_BOY +" = "+musteri.getBoy()+" , ");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET +" = "+musteri.getMemnuniyet()+",");
	sorgu.append(SUTUN_MUSTERI_GENELBILGILER_YAGORANI +" = "+musteri.getYagOrani()+" ");
	sorgu.append(" WHERE "+ SUTUN_MUSTERI_HAREKETLER_ID +" = "+musteri.getId());
	System.out.println(sorgu.toString());
	try(Statement statement = baglanti.createStatement()
	){
		statement.executeUpdate(sorgu.toString());
		System.out.println("Bilgiler Guncellendi");
		return true;
	}catch (SQLException e){
		System.out.println("Bilgiler Guncellennirken bir sorunla karsilasildi.");
		e.printStackTrace();
		return false;
	}
}
public ArrayList<Musteri> tumMusterileriGetir(){
	ArrayList<Musteri> musteriler = new ArrayList<>();
		StringBuilder sb = new StringBuilder("SELECT * FROM "); //�lgili eleman veri tabanindan cekiliyor.
	sb.append(TABLE_MUSTERI_GENELBILGILER);
	try (Statement statement = baglanti.createStatement();
		 ResultSet sonuc = statement.executeQuery(sb.toString())) {
		while(sonuc.next()) {
			Musteri eklenecekMusteri= new Musteri(sonuc.getString(SUTUN_MUSTERI_GEBELBILGILER_ADI),sonuc.getString(SUTUN_MUSTERI_GENELBILGILER_SOYADI),sonuc.getString(SUTUN_MUSTERI_GENELBILGILER_SIFRESI),sonuc.getString(SUTUN_MUSTERI_GENELBILGILER_CINSIYETI));
			eklenecekMusteri.setId(sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_ID));
			eklenecekMusteri.setKutle(sonuc.getDouble(SUTUN_MUSTERI_GENELBILGILER_KUTLE));
			eklenecekMusteri.setBoy(sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_BOY));
			eklenecekMusteri.setKasOrani(sonuc.getDouble(SUTUN_MUSTERI_GENELBILGILER_KASORANI));
			eklenecekMusteri.setYagOrani(sonuc.getDouble(SUTUN_MUSTERI_GENELBILGILER_YAGORANI));
			eklenecekMusteri.setMemnuniyet(sonuc.getInt(SUTUN_MUSTERI_GENELBILGILER_MEMNUNIYET));
			System.out.println(eklenecekMusteri.toString());
			musteriler.add(eklenecekMusteri);
		}
		return musteriler;
	} catch (SQLException e){
		System.out.println("Sorgu Basarisiz");
		e.printStackTrace();
		return null;
	}
}
	public int musteriIDgetir( ) {
		String sorgu = "SELECT seq  from sqlite_sequence where name='"+TABLE_MUSTERI_GENELBILGILER+"'";
		System.out.println(sorgu);
		try (Statement statement = baglanti.createStatement(); ResultSet sonuc = statement.executeQuery(sorgu)
		) {

			if (sonuc.next()) return Integer.parseInt(sonuc.getString(1));
			else return 0;
		} catch (SQLException e) {
			System.out.println("Musteri id cekilemedi");
			e.printStackTrace();
			return 0;
		}
	}
}
