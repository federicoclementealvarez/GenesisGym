package dataAccess;
import entities.*;
import java.sql.*;
import java.time.LocalDate;

public class DataPeople {

	public People getByDniAndPass (People p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		People peopleFound = null;
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select client.*, teacher.name teacher_name, teacher.lastname teacher_lastname, teacher.phone_number teacher_phone_number\r\n"
							+ "from people client\r\n"
							+ "left join people teacher\r\n"
							+ "	on client.dni_teacher=teacher.dni\r\n"
							+ "where client.dni=? and client.password=?");
			stmt.setInt(1,p.getDni());
			stmt.setString(2,p.getPassword());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				peopleFound = new People(
				rs.getInt("dni"),
				rs.getString("name"),
				rs.getString("lastname"),
				rs.getObject(("birthdate"),LocalDate.class),
				rs.getString("password"),
				rs.getString("phone_number"),
				rs.getString("type"),
				rs.getInt("id_plan"));
				
				if(rs.getObject("dni_teacher")!=null) {
					peopleFound.setTeacher(new People(rs.getInt("dni_teacher"), rs.getString("teacher_name"), rs.getString("teacher_lastname"), rs.getString("teacher_phone_number")));
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
		return(peopleFound);
	}
	
	public People getBasicInfo (int peopleDni) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		People peopleFound = null;
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("select dni, name, lastname, phone_number from people where dni=?");
			stmt.setInt(1,peopleDni);
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				peopleFound = new People(
				rs.getInt("dni"),
				rs.getString("name"),
				rs.getString("lastname"),
				rs.getString("phone_number"));
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
		return(peopleFound);
	}
	
	
	public boolean peopleExists(People p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean exists=false;
		try {
			stmt= DbConnector.getInstancia().getConn()
					.prepareStatement("Select dni from people where dni=?");
			stmt.setInt(1,p.getDni());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				exists=true;
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
		return(exists);
	}
	
	public void insertOne(People p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement("insert into people(dni, name, lastname, birthdate, password, phone_number, type, id_plan, dni_teacher) values(?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, p.getDni());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getLastName());
			stmt.setObject(4, p.getBirthDate());
			stmt.setString(5, p.getPassword());
			stmt.setString(6, p.getPhoneNumber());
			stmt.setString(7, p.getType());
			stmt.setInt(8, p.getPlan().getId());
			stmt.setObject(9,  (p.getTeacher()==null? null:p.getTeacher().getDni())  );
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
