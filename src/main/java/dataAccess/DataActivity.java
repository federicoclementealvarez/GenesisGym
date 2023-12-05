package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Activity;
import entities.Plan;

public class DataActivity {

	public ArrayList<Activity> getByPlan(Plan plan) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select a.name\r\n"
							+ "from activity a\r\n"
							+ "inner join plan_activity pa\r\n"
							+ "	on pa.id_activity=a.id\r\n"
							+ "where pa.id_plan=?");
			stmt.setInt(1, plan.getId());
			rs = stmt.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					Activity act = new Activity(rs.getString("name"));
					activities.add(act);
				}
			}
		}
		catch (SQLException ex){
			ex.getStackTrace();
		}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			}
			catch (SQLException ex) {
				ex.getStackTrace();
			}
		}
		return(activities);
	}
	
}
