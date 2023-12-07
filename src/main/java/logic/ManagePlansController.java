package logic;

import java.util.ArrayList;

import dataAccess.DataActivity;
import entities.Activity;
import entities.Plan;

public class ManagePlansController {
	private DataActivity da;
	
	public ArrayList<Activity> getActivitiesByPlan(Plan plan) {
		da = new DataActivity();
		return(da.getByPlan(plan));
	}
	
}
