package entities;
import java.time.LocalTime;

public class Exercise {

	private int id;
	private Routine routine; 
	private ExerciseType exerciseType;
	private int repetitionQuantity;
	private int setQuantity;
	private LocalTime restTimeBetweenSets;
	
	
	public Routine getRoutine() {
		return routine;
	}
	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	public ExerciseType getExerciseType() {
		return exerciseType;
	}
	public void setExerciseType(ExerciseType exerciseType) {
		this.exerciseType = exerciseType;
	}
	public int getRepetitionQuantity() {
		return repetitionQuantity;
	}
	public void setRepetitionQuantity(int repetitionQuantity) {
		this.repetitionQuantity = repetitionQuantity;
	}
	public int getSetQuantity() {
		return setQuantity;
	}
	public void setSetQuantity(int setQuantity) {
		this.setQuantity = setQuantity;
	}
	public LocalTime getRestTimeBetweenSets() {
		return restTimeBetweenSets;
	}
	public void setRestTimeBetweenSets(LocalTime restTimeBetweenSets) {
		this.restTimeBetweenSets = restTimeBetweenSets;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
