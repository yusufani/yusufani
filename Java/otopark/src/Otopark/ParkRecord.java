package Otopark;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class ParkRecord extends RecursiveTreeObject<ParkRecord> {
private final Time enterTime;
private Time  exitTime=null;
private final Vehicle vehicle2;


	private double fee;
public String getVehicle2(){
	return vehicle2.toString();
}
public Vehicle getVehicle() {
	return vehicle2;
}
public ParkRecord(Time enterTime,Vehicle vehicle) {
	this.enterTime=enterTime;
	this.vehicle2=vehicle;
}
	public void setExitTime(Time exitTime) { // Exception Ekle
	this.exitTime = exitTime;
}

	public String getExitTime() {
		if(exitTime== null ) return "null";
		return exitTime.toString();
	}

	public String getEnterTime() {
		if(enterTime== null ) return "null";
			return enterTime.toString();
	}

	public int  getParkingDuration() {
	// If the vehicle doesnt have exit time return -1
	if(exitTime == null ) return -1 ;
	return enterTime.getDifference(exitTime);

}
	public Time getExitTime2() {return exitTime;}
	public Time getEnterTime2() {return enterTime;}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
}
