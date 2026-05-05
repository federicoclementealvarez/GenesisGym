package logic;

import java.util.ArrayList;
import dataAccess.DataClass;
import dataAccess.DataActivity;
import dataAccess.DataPeople;
import entities.Class;
import entities.People;
import entities.Activity;

public class ManageClassController {

	private DataClass dc;
	private DataActivity da;
	private DataPeople dp;
	
	public ManageClassController() {
		dc = new DataClass();
		da = new DataActivity();
		dp = new DataPeople();
	}
	
	public ArrayList<People> getInscribedInClass(int idClass) {
		return dp.getInscribedInClass(idClass);
	}
	
	public ArrayList<Class> getClassesByPeople(People p){
		return dc.getByPeople(p);
	}
	
	public void deleteEnrollment(People p, int idClass) {
		dc.deleteEnrollment(p.getDni(), idClass);
	}
	
	public void addEnrollment(People p, int idClass) {
		dc.addEnrollment(p.getDni(), idClass);
	}
	
	public ArrayList<Class> getAvailableClasses(People p) {
		return dc.getAvailableClasses(p);
	}

	public ArrayList<Class> getTaughtClasses(People teacher) {
		return dc.getByTeacher(teacher);
	}

	public boolean deleteClass(int idClass) {
		if (!dc.hasAttendants(idClass)) {
			dc.deleteOne(idClass);
			return true;
		}
		return false;
	}

	public void createClass(Class c) {
		dc.insertOne(c);
	}

	public ArrayList<Activity> getAllActivities() {
		return da.getAll();
	}
	
	public ArrayList<Activity> getAllTeacheableActivities() {
		return da.getAllTeacheable();
	}
	
	public Activity getActivityById(int id) {
		return da.getOne(id);
	}
}
