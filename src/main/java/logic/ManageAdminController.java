package logic;

import java.util.ArrayList;
import dataAccess.DataExerciseType;
import dataAccess.DataPlan;
import dataAccess.DataActivity;
import entities.ExerciseType;
import entities.Plan;
import entities.Activity;

public class ManageAdminController {

	private DataExerciseType det;
	private DataPlan dp;
	private DataActivity da;

	public ManageAdminController() {
		det = new DataExerciseType();
		dp = new DataPlan();
		da = new DataActivity();
	}

	public ArrayList<ExerciseType> getAllExerciseTypes() {
		return det.getAll();
	}

	public boolean deleteExerciseType(int id) {
		if (!det.isUsed(id)) {
			det.deleteOne(id);
			return true;
		}
		return false;
	}

	public void createExerciseType(ExerciseType et, int idActivity) {
		det.insertOne(et, idActivity);
	}

	// Plan
	public ArrayList<Plan> getAllPlans() {
		return dp.getAll();
	}

	public boolean deletePlan(int id) {
		if (!dp.isUsed(id)) {
			dp.deleteOne(id);
			return true;
		}
		return false;
	}

	public void createPlan(Plan p, String[] activityIds) {
		dp.insertOne(p, activityIds);
	}

	public ArrayList<Activity> getNonTeacheableActivities() {
		return da.getNonTeacheable();
	}

	public ArrayList<Activity> getAllActivities() {
		return da.getAll();
	}
}
