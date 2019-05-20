package Otopark;


import java.util.ArrayList;

public class AutoPark {
private double hourlyFee,incomeDaily;
private ArrayList<ParkRecord> parkRecords;
private ArrayList<SubscribedVehicle> subscribedVehicles;
private int capacity;
private int recordIndex,vehicleIndex;
private static AutoPark single_instance= null;

	public ArrayList<SubscribedVehicle> getSubscribedVehicles() {
		return subscribedVehicles;

	}
public AutoPark(){
	parkRecords=new ArrayList<>();
	subscribedVehicles=new ArrayList<>();
	incomeDaily=0;
}
	public AutoPark(double hourlyFee, int capacity) { //GUI de capacity ve hourlyFee leri kontrol et
	this.hourlyFee = hourlyFee;
	this.capacity = capacity;
	parkRecords=new ArrayList<>();
	subscribedVehicles=new ArrayList<>();
	incomeDaily=0;
	single_instance=this;
}

	public void setHourlyFee(double hourlyFee) {
		this.hourlyFee = hourlyFee;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public static AutoPark getInstance(){
		{
			if(single_instance == null ) single_instance=new AutoPark();
			return single_instance;
		}
}
public SubscribedVehicle searchVehicle(String plate) {
	// This method search subscribedVehicles ArrayList if there is a same plate with argument plate returns it's Object
	// If cant find returns null
	for(SubscribedVehicle i : subscribedVehicles) {
		if(i.getPlate().equals(plate)) return i;
	}
	return null;
}
public boolean vehicleEnters(String plate , Time enter , Boolean isOffical) { // GUI de kontrol
	//This method add vehicle to Autopark system
	if (isParked(plate ) ) return false ;
	Vehicle vehicle;
		if ((vehicle = searchVehicle(plate)) == null) {// Eger adamin uyeligi valid degilse normal arac gibi mi
			if (isOffical) vehicle = new OfficialVehicle(plate);
			else  vehicle = new RegularVehicle(plate);
		}
		parkRecords.add(new ParkRecord(enter, vehicle));
		return true;
}

	public double getHourlyFee() {
		return hourlyFee;
	}

	public void setIncomeDaily(double incomeDaily) {
		this.incomeDaily = incomeDaily;
	}

	public ArrayList<ParkRecord> getParkRecords() {
		return parkRecords;
	}

	public void setParkRecords(ArrayList<ParkRecord> parkRecords) {
		this.parkRecords = parkRecords;
	}

	public void setSubscribedVehicles(ArrayList<SubscribedVehicle> subscribedVehicles) {
		this.subscribedVehicles = subscribedVehicles;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getRecordIndex() {
		return recordIndex;
	}

	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}

	public int getVehicleIndex() {
		return vehicleIndex;
	}

	public void setVehicleIndex(int vehicleIndex) {
		this.vehicleIndex = vehicleIndex;
	}

	public boolean vehicleExits(String plate , Time exit) {
	if(!isParked(plate)) return false;
	try {
		ParkRecord exitRecord = searchParkedArray(plate);
		exitRecord.setExitTime(exit);
		double fee;
		if(exitRecord.getVehicle() instanceof SubscribedVehicle){
			if(( exitRecord.getVehicle()).getSubscription().isValid()) fee =0;
			else{
				// If there subscription was ended . It has to pay subscriber amounts % 30
				int duration =0;// For sucriber amount
				int durationReg = 0; // For regular amount
				for(ParkRecord i : parkRecords ){
					//If plate are same and Times ara betwwen subscriber times add to duration

					if(i.getVehicle().getPlate().equals(plate) && i.getParkingDuration()> 0.0){
						// If times between subscriber Times
						int x =i.getVehicle().getSubscription().getBegin().valueOfDate();
						int y = exit.getDateofDay().valueOfDate();
						int z = i.getVehicle().getSubscription().getEnd().valueOfDate();
						if( i.getVehicle().getSubscription().getBegin().valueOfDate()<exit.getDateofDay().valueOfDate() &&i.getVehicle().getSubscription().getEnd().valueOfDate()>exit.getDateofDay().valueOfDate()){
							duration += ( i.getParkingDuration());
						}
						// if not
						else if (i.getVehicle().getSubscription().getEnd().valueOfDate()<exit.getDateofDay().valueOfDate()) {
							durationReg+=i.getParkingDuration();
						}
					}
				}
				fee=calculateFee(duration);
				fee/=70; // There is a discount for subscribers
				fee+=calculateFee(durationReg);
			}
		}
		else if  (exitRecord.getVehicle() instanceof  RegularVehicle ){
			fee= calculateFee(exitRecord.getParkingDuration());
		}
		else {
			fee=0;
		}
		//Eger Oderse
		incomeDaily+=fee;
		return true;
	}
	catch (NoSuchFieldException e){
		e.printStackTrace();
		return false ;
	}
}
public boolean isParked ( String plate ) {
	//This method search vehicles plate in the parkRecors ArrayList
	for( ParkRecord i : parkRecords) {
		if ( i.getVehicle().getPlate().equals(plate) && i.getExitTime().equals("null") ) return true;
	}
	return false ;
}


	public double getIncomeDaily() {
		return incomeDaily;
	}

	public ParkRecord searchParkedArray (String plate )throws NoSuchFieldException  {
	for( ParkRecord i : parkRecords) {
		if ( i.getVehicle().getPlate().equals(plate) ) return i ;
	}
	throw new NoSuchFieldException("Boyle bir arac bulunamadi");
}

public boolean addVehicle ( SubscribedVehicle vehicle ) throws Exception {
	//This method add Vehicle to subscribedVehicles arraylist if there is same vehicle  in the arraylist returns false
	//Else if can add to arrayList returns true
	SubscribedVehicle newVehicle = searchVehicle(vehicle.getPlate());
	if ((newVehicle == null)) {
		subscribedVehicles.add(vehicle);
		return true;
	}
	throw new Exception("This Vehicle Has Been Already Rolled");

}
public int getCurrentParkedCount(){
		int size=0;
		for(ParkRecord i : parkRecords){
			if (i.getExitTime().equals("null")) size++;
		}
		return size;
}
private double calculateFee(int duration){
	//Duration is terms of minute
	duration = duration/60 + (duration%60)/30;
	return  hourlyFee * ((double) duration);
}
}