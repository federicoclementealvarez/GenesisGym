package entities;

public class Activity {

	private int id;
	private String name;
	private String description;
	private boolean teacheable;
	
	
	public Activity(int id, String name, String description, boolean teacheable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.teacheable = teacheable;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isTeacheable() {
		return teacheable;
	}
	public void setTeacheable(boolean teacheable) {
		this.teacheable = teacheable;
	}
	
	
}
