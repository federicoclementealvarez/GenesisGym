package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.ExerciseType;
import entities.Plan;

public class DataExerciseType {

	public ArrayList<ExerciseType> getByPlan(Plan plan) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ExerciseType> exTypes = new ArrayList<ExerciseType>();
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select et.id, et.name\r\n"
							+ "from exercise_type et\r\n"
							+ "inner join activity a\r\n"
							+ "	on a.id=et.id_activity\r\n"
							+ "inner join plan_activity pa\r\n"
							+ "	on pa.id_activity=a.id\r\n"
							+ "where pa.id_plan=?");
			stmt.setInt(1,plan.getId());
			rs = stmt.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					ExerciseType et = new ExerciseType(
							rs.getInt("id"),
							rs.getString("name")
					);
					
					exTypes.add(et);
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
		return(exTypes);
	}
	
}