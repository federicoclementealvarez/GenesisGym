package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import entities.Exercise;
import entities.ExerciseType;
import entities.Routine;

public class DataExercise {

	public ArrayList<Exercise> getByRoutine(Routine r) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select e.id, e.set_quantity, e.repetition_quantity, e.rest_time_between_sets, et.name et_name, et.description et_description\r\n"
							+ "from exercise e\r\n"
							+ "inner join exercise_type et\r\n"
							+ "	on et.id=e.id_exercise_type\r\n"
							+ "where e.id_routine=?\r\n"
							+ "order by e.id");
			stmt.setInt(1,r.getId());
			rs = stmt.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					ExerciseType et = new ExerciseType(rs.getString("et_name"), rs.getString("et_description"));
					
					Exercise e = new Exercise(
							rs.getInt("id"),
							et,
							rs.getInt("repetition_quantity"),
							rs.getInt("set_quantity"),
							rs.getObject(("rest_time_between_sets"),LocalTime.class));
					
					exercises.add(e);
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
		return(exercises);
	}
	
	public void deleteByRoutine(int exerciseId) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement("delete from exercise where id=?");
			stmt.setInt(1, exerciseId);
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void insertOne(Exercise ex) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement("insert into exercise \r\n"
							+ "(id_routine, id_exercise_type, set_quantity, repetition_quantity, rest_time_between_sets)\r\n"
							+ "values (?,?,?,?,?)");
			stmt.setInt(1, ex.getRoutine().getId());
			stmt.setInt(2, ex.getExerciseType().getId());
			stmt.setInt(3, ex.getSetQuantity());
			stmt.setInt(4, ex.getRepetitionQuantity());
			stmt.setObject(5, ex.getRestTimeBetweenSets());
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
}
