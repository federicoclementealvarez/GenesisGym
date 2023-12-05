package entities;
import java.util.LinkedList;

public class Plan {

	private int id;
	private String name;
	private Double rate;
	private LinkedList<Activity> activities;
	
	public Plan(int id, String name, Double rate) {
		this.id = id;
		this.name = name;
		this.rate = rate;
		activities = new LinkedList<Activity>();
	}
	
	public Plan(int id) {
		this.id = id;
		activities = new LinkedList<Activity>();
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
	public LinkedList<Activity> getActivities() {
		return activities;
	}
	public void setActivities(LinkedList<Activity> activities) {
		this.activities = activities;
	}
	
	
}
