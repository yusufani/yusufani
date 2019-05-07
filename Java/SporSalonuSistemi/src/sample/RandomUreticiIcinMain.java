package sample;
public class RandomUreticiIcinMain {
	public static void main(String[] args) {
		RandomUretici r=new RandomUretici();
		VeriTabani x=VeriTabani.getInstance();
		x.VeriTabaniniAc();
		for(int i=0;i<500; i++)
		r.MusteriUret();
		x.VeriTabaniniKapa();
	}
}
