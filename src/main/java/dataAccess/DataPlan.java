package dataAccess;
import entities.*;
import java.sql.*;
import java.util.ArrayList;

public class DataPlan {

	public Plan getPlanByName(String planName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Plan planFound=null;
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("Select * from plan where name=?");
			stmt.setString(1,planName);
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				planFound = new Plan(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDouble("rate"));
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
		return(planFound);
	}
	
	public Plan getOne(int planId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Plan planFound=null;
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("Select * from plan where id=?");
			stmt.setInt(1,planId);
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				planFound = new Plan(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDouble("rate"));
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
		return(planFound);
	}
	
	public ArrayList<Plan> getAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Plan> plans = new ArrayList<Plan>();
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select p.id as planId, p.name as planName, p.rate as planRate, a.id as activityId, a.name as activityName, a.description as activityDescription, a.teacheable as activityTeacheable\r\n"
							+ "from plan p\r\n"
							+ "inner join plan_activity pa on pa.id_plan=p.id\r\n"
							+ "inner join activity a on pa.id_activity=a.id\r\n"
							+ "where p.name<>'Profesor'");
			rs = stmt.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					Activity act = new Activity(rs.getInt("activityId"),rs.getString("activityName"),rs.getString("activityDescription"),rs.getInt("activityTeacheable")==1?true:false);
					
					int position=0;
					for(Plan p: plans) {
						if (rs.getInt("planId")==p.getId()) {
							break;
						}
						position++;
					}
					
					if(position>(plans.size()-1)) {
						Plan plan = new Plan(rs.getInt("planId"),rs.getString("planName"),rs.getDouble("planRate"));
						plan.getActivities().add(act);
						plans.add(plan);
					}
					else {
						Plan plan = plans.get(position);
						plan.getActivities().add(act);
						plans.set(position,plan);
					}
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
		return(plans);
	}
	
}
