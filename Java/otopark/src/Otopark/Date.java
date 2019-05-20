package Otopark;
public  class Date {
int day , month , year ;
private static Date today;
public Date(int day, int month, int year) { // GUI'DE DATELERI KONTROL ET
	this.day = day;
	this.month = month;
	this.year = year;
}

public boolean isAfterThan(Date other ) {
    return this.valueOfDate() > other.valueOfDate();
}
public boolean isBeforeThan(Date other ) {
    return this.valueOfDate() < other.valueOfDate();
}
public boolean isEqualsWith(Date other ) {
    return this.valueOfDate() == other.valueOfDate();
}
public static Date getToday() {
	return today;
}
public static void setToday(Date dateOfToday ){ today=dateOfToday; }
public int valueOfDate() {
	return (this.year * 525960) +(this.month *43200)  + (this.day*1440);// All values ​​are in minutes
}
private void SetDate(int day, int month , int year) { //GUI'DE DATELERI KONTROL ET 
	today.day=day;
	today.month=month; 
	today.year=year;
}

	@Override
	public String toString() {
		return day+"/"+month+"/"+year;
	}
}
