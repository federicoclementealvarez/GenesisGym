package entities;
import java.time.LocalDate;

public class People {

	private int dni;
	private String name;
	private String lastName;
	private LocalDate birthDate;
	private String password;
	private String phoneNumber;
	private String type; //teacher or normal client
	private Plan plan;
	private People teacher=null;
	
	
	public People(int dni) {
		this.dni = dni;
	};
	
	public People(int dni, String name, String lastName, String phoneNumber) {
		this.dni = dni;
		this.name=name;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
	};
	
	public People(int dni, String name, String lastName, LocalDate birthDate, String password, String phoneNumber, String type) {
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}
	
	
	public People(int dni, String name, String lastName, LocalDate birthDate, String password, String phoneNumber, String type, int planId) {
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.plan = new Plan(planId);
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate bithDate) {
		this.birthDate = bithDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public People getTeacher() {
		return teacher;
	}
	public void setTeacher(People teacher) {
		this.teacher = teacher;
	}
	
	
}
