package entities;
import java.time.LocalTime;

public class Exercise {

	private int id;
	private Routine routine; 
	private ExerciseType exerciseType;
	private int repetitionQuantity;
	private int setQuantity;
	private LocalTime restTimeBetweenSets;
	
	
	public Exercise(int id, ExerciseType exerciseType, int repetitionQuantity, int setQuantity, LocalTime restTimeBetweenSets) {
		this.id = id;
		this.exerciseType = exerciseType;
		this.repetitionQuantity = repetitionQuantity;
		this.setQuantity = setQuantity;
		this.restTimeBetweenSets = restTimeBetweenSets;
	}
	
	public Exercise(ExerciseType exerciseType, int repetitionQuantity, int setQuantity, Routine routine) {
		this.exerciseType = exerciseType;
		this.repetitionQuantity = repetitionQuantity;
		this.setQuantity = setQuantity;
		this.routine = routine;
	}
	
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
	
	public int getRestTimeBetweenSetsInSeconds() {
		String[] timeArray = this.getRestTimeBetweenSets().toString().split(":");
		int seconds = Integer.parseInt(timeArray[0])*60*60+Integer.parseInt(timeArray[1])*60+Integer.parseInt(timeArray[2]);
		return(seconds);
	}
	
	public void setRestTimeBetweenSetsInSeconds(int secondsInput) {
		int hours = secondsInput/3600;
		int minutes = (secondsInput-hours*3600)/60;
		int seconds = (secondsInput-hours*3600-minutes*60);
		
		String hoursString = Integer.toString(hours);
		String minutesString = Integer.toString(minutes);
		String secondsString = Integer.toString(seconds);
		
		if(hours<10) {
			hoursString = "0"+hoursString;
		}
		if(minutes<10) {
			minutesString = "0"+minutesString;
		}
		if(seconds<10) {
			secondsString = "0"+secondsString;
		}
		
		String timeString = hoursString+":"+minutesString+":"+secondsString;
		
		this.restTimeBetweenSets = LocalTime.parse(timeString);
	}
	
}
