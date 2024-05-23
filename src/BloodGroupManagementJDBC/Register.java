package BloodGroupManagementJDBC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

public class Register {
	private int id;
public static int idCounter=1;
	private String name;
	private long contact;
	private String dob;
	private int age;
	private String state;
	private String city;
private String bloodGroup;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Register(int id, String name, long contact, String dob, int age, String state, String city,String bloodGroup) {

		this.id = id;
		this.name = name;
		this.contact = contact;
		this.dob = dob;
		this.age = age;
		this.state = state;
		this.city = city;
		this.bloodGroup=bloodGroup;

	}

	public String toString() {

		return "{" + id + "," + name + "," + contact + "," + dob + "," + age + "," + state + "," + city + "," +bloodGroup+ "}";
	}

	public static Date dateCalc(String dob) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		try {
			Date date = dateFormat.parse(dob);

			return date;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static int ageCalculation(String dob) {

		LocalDate ageCalculation = LocalDate.parse(dob);
		LocalDate currentDate = LocalDate.now();

		Period age = Period.between(ageCalculation, currentDate);

		return age.getYears();

	}

	
	public static int idCounter() {
		
		return idCounter++;
	}
	
	
	
	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}



}
