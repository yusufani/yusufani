package Otopark;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class SubscribedVehicle extends RecursiveTreeObject<SubscribedVehicle> implements Vehicle {
	private Subscription subscription;
	private final String plate;
	
	public SubscribedVehicle(String plate , Subscription subscription) { //GUIDE KONTROL
		this.plate = plate.toUpperCase();
		this.subscription=subscription;
	}

	public SubscribedVehicle(String plate ){
		this.plate=plate.toUpperCase();
		subscription=null;
	}

	@Override
	public String getPlate() {
		// TODO Auto-generated method stub
		return plate;
	}

	@Override
	public Subscription getSubscription() {
		// TODO Auto-generated method stub
		return subscription;
	}
public void setSubscription(Subscription subscription){
		this.subscription=subscription;
}
	@Override
	public boolean isSpecial() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Plate: "+plate + "\t"+ "Type: Subscribed Vehicle ";
	}

	public String getBegin(){return subscription.getBegin().toString();}
public String getEnd(){return subscription.getEnd().toString();}
}
