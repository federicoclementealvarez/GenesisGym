package entities;

public class Routine {

	private int id;
	private String name;
	private People client;
	private int exerciseQuantity;
	private String type;
	
	
	public Routine(int id, String name, int exerciseQuantity, String type) {
		this.name = name;
		this.type = type;
		this.id = id;
		this.exerciseQuantity = exerciseQuantity;
	}
	
	public Routine(String name, String type, People client) {
		this.name = name;
		this.type = type;
		this.client=client;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public People getClient() {
		return client;
	}
	public void setClient(People client) {
		this.client = client;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExerciseQuantity() {
		return exerciseQuantity;
	}
	public void setExerciseQuantity(int exerciseQuantity) {
		this.exerciseQuantity = exerciseQuantity;
	}
	
	
}
