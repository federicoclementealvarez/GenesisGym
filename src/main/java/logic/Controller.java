package logic;
import entities.*;

import java.util.ArrayList;

import dataAccess.*;

public class Controller {

	private DataPeople dp;
	private DataPlan dpl;
	private DataRoutine dr;
	private DataExercise de;
	private DataExerciseType det;
	private DataActivity da;
	
	public People getByDniAndPass(People p) {
		dp=new DataPeople();
		return(dp.getByDniAndPass(p));
	}
	
	public boolean peopleExists(People p) {
		dp=new DataPeople();
		return(dp.peopleExists(p));
	}
	
	public void insertPeople(People p) {
		dp=new DataPeople();
		dp.insertOne(p);
	}
	
	public Plan getPlanByName(String planName) {
		dpl=new DataPlan();
		return(dpl.getPlanByName(planName));
	}

	public ArrayList<Plan> getAllPlans() {
		dpl=new DataPlan();
		return(dpl.getAll());
	}
	
	public Plan getPlanById(int planId) {
		dpl=new DataPlan();
		return(dpl.getOne(planId));
	}
	
	public People getTeacherBasicInfo(int peopleId) {
		dp=new DataPeople();
		return(dp.getBasicInfo(peopleId));
	}
	
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
	
	public ArrayList<Activity> getActivitiesByPlan(Plan plan) {
		da = new DataActivity();
		return(da.getByPlan(plan));
	}
	
	public void deleteRoutine(Routine r) {
		dr=new DataRoutine();
		dr.deleteOne(r);
	}
	
}
