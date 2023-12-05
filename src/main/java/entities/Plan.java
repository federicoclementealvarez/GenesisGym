package entities;
import java.util.ArrayList;

public class Plan {

	private int id;
	private String name;
	private Double rate;
	private ArrayList<Activity> activities;
	
	public Plan(int id, String name, Double rate) {
		this.id = id;
		this.name = name;
		this.rate = rate;
		activities = new ArrayList<Activity>();
	}
	
	public Plan(int id) {
		this.id = id;
		activities = new ArrayList<Activity>();
	}
	
	
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
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public ArrayList<Activity> getActivities() {
		return activities;
	}
	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}
	
	
}
