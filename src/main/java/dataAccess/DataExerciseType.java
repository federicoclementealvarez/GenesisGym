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
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			}
			catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
		return(exTypes);
	}

	public ArrayList<ExerciseType> getAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ExerciseType> exTypes = new ArrayList<ExerciseType>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select et.*, a.name activity_name from exercise_type et inner join activity a on a.id = et.id_activity");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ExerciseType et = new ExerciseType(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description")
					);
					exTypes.add(et);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
		return exTypes;
	}

	public void insertOne(ExerciseType et, int idActivity) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into exercise_type(name, description, id_activity) values(?,?,?)");
			stmt.setString(1, et.getName());
			stmt.setString(2, et.getDescription());
			stmt.setInt(3, idActivity);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public boolean isUsed(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean used = false;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select count(*) count from exercise where id_exercise_type = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				used = rs.getInt("count") > 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
		return used;
	}

	public void deleteOne(int id) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from exercise_type where id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}