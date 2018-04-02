//Jonathan Phu
//500776576
//March 21,2017
//CPS209

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Appointment implements Comparable<Appointment>{

	//instance variables
	private Calendar date;
	private String description;
	private Person person;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	//constructors
	public Appointment(){
		date = new GregorianCalendar();	
		person = new Person();
	}
	public Appointment(int year,int month,int day,int hour,int minute,String description, Person person){
		date = new GregorianCalendar(year,month,day,hour,minute);	
		this.description = description;
		this.person = person;
	}
	
	
	//get and set methods for year, month, etc
	public int getYear(){return date.get(Calendar.YEAR);}
	public void setYear(int year){date.set(Calendar.YEAR, year);}
	
	public int getMonth(){return date.get(Calendar.MONTH);}
	public void setMonth(int month){date.set(Calendar.MONTH, month);}
	
	public int getDay(){return date.get(Calendar.DAY_OF_MONTH);}
	public void setDay(int day){date.set(Calendar.DAY_OF_MONTH, day);}
	
	public int getHour(){return date.get(Calendar.HOUR_OF_DAY);}
	public void setHour(int hour){date.set(Calendar.HOUR_OF_DAY, hour);}
	
	public int getMinute(){return date.get(Calendar.MINUTE);}
	public void setMinute(int minute){date.set(Calendar.MINUTE, minute);}
	
	public Person getPerson(){return person;}
	public void setPerson(Person person){this.person = person;}
	
	
	//prints time and description in proper format
	public String print(){
		return sdf.format(date.getTime()) + " " + description;  
	}
	
	public boolean occursOn(int year,int month,int day){
		//if method to check if dates are the same
		if ( year == date.get(Calendar.YEAR) && month == date.get(Calendar.MONTH) && 
			day == date.get(Calendar.DAY_OF_MONTH))
			return true;
		//return false by default
		return false;

	}
	//overriding compareTo method
	public int compareTo(Appointment appointment) {
		return this.date.compareTo(appointment.date);
	}
	
}
