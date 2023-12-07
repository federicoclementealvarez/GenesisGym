package logic;

import java.util.ArrayList;

import dataAccess.DataExercise;
import dataAccess.DataExerciseType;
import entities.Exercise;
import entities.ExerciseType;
import entities.Plan;

public class ManageExerciseController {

	private DataExercise de;
	private DataExerciseType det;
	
	public void deleteExerciseById(int exerciseId) {
		de = new DataExercise();
		de.deleteByRoutine(exerciseId);
	}
	
	public ArrayList<ExerciseType> getExerciseTypesByPlan(Plan plan){
		det = new DataExerciseType();
		return(det.getByPlan(plan));
	}
	
	public void insertExercise(Exercise e) {
		de=new DataExercise();
		de.insertOne(e);
	}
	
}
