package Otopark;

public class OfficialVehicle implements Vehicle {
	private final String plate;
	public OfficialVehicle(String plate) {
		this.plate = plate.toUpperCase();
	}

	@Override
	public String getPlate() {
		// TODO Auto-generated method stub
		return plate;
	}

	@Override
	public Subscription getSubscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSpecial() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return  "Plate: "+plate + "\t"+ "Type: Offical Vehicle";
	}
}
