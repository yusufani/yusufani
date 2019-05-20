package Otopark;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoPark autoPark = new AutoPark(1, 10);
		SubscribedVehicle subs = null ;
		try{ subs = new SubscribedVehicle("2134",new Subscription(new Date(5,6,2019),new Date(10,6,2019),"2134"));
		}catch (Exception e ){
			e.printStackTrace();
		}

		RegularVehicle regular = new RegularVehicle("5666");
		OfficialVehicle polis = new OfficialVehicle("3");
		Date.setToday(new Date(11,6,2019));
		try {
			autoPark.addVehicle(subs);
		}catch (Exception e) {
	e.printStackTrace();
		}
		autoPark.vehicleEnters(subs.getPlate(),new Time(5,10,Date.getToday()),subs.isSpecial());
		autoPark.vehicleEnters(regular.getPlate(),new Time(10,34,Date.getToday()),regular.isSpecial());
		autoPark.vehicleEnters(polis.getPlate(),new Time(11,13,Date.getToday()),polis.isSpecial());
		System.out.println(autoPark.getIncomeDaily());
		System.out.println("Uye olan plakal:" + autoPark.searchVehicle("2134").getPlate());
		System.out.println("Parketti olmasi lazım " + autoPark.isParked("3"));
		System.out.println("Parketmedi olması lazim " + autoPark.isParked("5"));
		System.out.println("  True mu " +autoPark.vehicleExits("2134",new Time(16,3,new Date ( 15,6,2019))));
		System.out.println(autoPark.getIncomeDaily());
	}

}
