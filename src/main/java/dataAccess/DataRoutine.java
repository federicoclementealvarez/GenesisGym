package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.People;
import entities.Routine;

public class DataRoutine {

	
	public ArrayList<Routine> getByPeople(People p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Routine> routines = new ArrayList<Routine>();
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select r.id, r.name, r.type, count(e.id) exercise_quantity \r\n"
							+ "from routine r \r\n"
							+ "left join exercise e\r\n"
							+ "	on e.id_routine=r.id\r\n"
							+ "where r.dni_people=?\r\n"
							+ "group by r.id");
			stmt.setInt(1,p.getDni());
			rs = stmt.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					Routine r = new Routine(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("exercise_quantity"),
							rs.getString("type"));
					
					routines.add(r);
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
		return(routines);
	}
	
	public void insertOne(Routine r) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement("insert into routine(name, type, dni_people) values(?,?,?)");
			stmt.setString(1, r.getName());
			stmt.setString(2, r.getType());
			stmt.setInt(3, r.getClient().getDni());
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
