package logic;
import entities.*;

import java.util.ArrayList;

import dataAccess.*;

public class SignUpLoginController {

	private DataPeople dp;
	private DataPlan dpl;
	
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
	
}
