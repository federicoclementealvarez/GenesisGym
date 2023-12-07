package logic;

import java.util.ArrayList;

import dataAccess.DataExercise;
import dataAccess.DataRoutine;
import entities.Exercise;
import entities.People;
import entities.Routine;

public class ManageRoutineController {

	private DataRoutine dr;
	private DataExercise de;
	
	public ArrayList<Routine> getRoutinesByPeople(People p){
		dr=new DataRoutine();
		return(dr.getByPeople(p));
	}
	
	public void insertRoutine(Routine r) {
		dr=new DataRoutine();
		dr.insertOne(r);
	}

	public ArrayList<Exercise> getExercisesByRoutine(Routine r) {
		de = new DataExercise();
		return(de.getByRoutine(r));
	}
	
	public void deleteRoutine(Routine r) {
		dr=new DataRoutine();
		dr.deleteOne(r);
	}
	
}
