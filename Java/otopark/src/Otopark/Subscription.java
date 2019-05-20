package Otopark;


public class Subscription {
	private Date begin, end;
	private SubscribedVehicle vehicle;
	public Subscription(Date begin , Date end , String plate ) throws  Exception{
		if(begin.valueOfDate()>end.valueOfDate()) throw new Exception("Begin date can not be later than end date ");
		this.begin=begin;
		this.end = end;
		vehicle = new SubscribedVehicle(plate.toUpperCase() , this);
	}

	public Subscription(Date begin, Date end) {
		this.begin = begin;
		this.end = end;
	}

	public void  SetSubsriction(SubscribedVehicle vehicle){
		this.vehicle=vehicle;
	}

	public SubscribedVehicle getVehicle() {
		return vehicle;
	}

	public Date getBegin() {
		return begin;
	}

	public Date getEnd() {
		return end;
	}

	public boolean isValid(){
        return !Date.getToday().isAfterThan(end);
    }
}
