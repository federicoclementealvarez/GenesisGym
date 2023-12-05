package entities;

public class ExerciseType {

	private int id;
	private String name;
	private String description;
	private Activity activity;
	
	
	public ExerciseType(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public ExerciseType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ExerciseType(int id) {
		this.id = id;
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
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
}
