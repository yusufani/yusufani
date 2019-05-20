package Otopark;

public class Time {
private int hour,minute;
private Date dateofDay;
public Time(int hour, int minute, Date date ) {
	this.hour = hour;
	this.minute = minute;
	this.dateofDay=date;
}

public Time(int hour , int minute ){
	this.hour = hour;
	this.minute = minute;
	this.dateofDay=Date.getToday();
}
public int getDifference(Time other) {
	return Math.abs((this.dateofDay.valueOfDate()-other.dateofDay.valueOfDate()) +(hour-other.hour)*60+minute- other.minute);
}

	public Date getDateofDay() {
		return dateofDay;
	}

	public int getValue (){
	return dateofDay.valueOfDate()+hour*60+minute;
}

	@Override
	public String toString() {
		return "Time :(HH:MM)"+hour+":"+minute+"  "+"Date:"+dateofDay.toString();
	}
}
