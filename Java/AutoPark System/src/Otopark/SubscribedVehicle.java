package Otopark;

public class SubscribedVehicle implements Vehicle {
	private Subscription subscription;
	private final String plate;
	
	public SubscribedVehicle(String plate , Subscription subscription) { //GUIDE KONTROL
		this.plate = plate;
		this.subscription=subscription;
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
