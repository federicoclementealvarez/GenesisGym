package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import entities.Activity;
import entities.Class;
import entities.People;

public class DataClass {

	public ArrayList<Class> getByPeople(People p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Class> classes = new ArrayList<Class>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select c.*, a.name activity_name, t.name teacher_name, t.lastname teacher_lastname, t.phone_number teacher_phone_number \r\n"
							+ "from class c \r\n"
							+ "inner join people_class pc on c.id = pc.id_class \r\n"
							+ "inner join activity a on c.id_activity = a.id \r\n"
							+ "inner join people t on c.dni_teacher = t.dni \r\n"
							+ "where pc.dni_people = ?");
			stmt.setInt(1, p.getDni());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					People teacher = new People(
							rs.getInt("dni_teacher"),
							rs.getString("teacher_name"),
							rs.getString("teacher_lastname"),
							rs.getString("teacher_phone_number")
					);
					
					Activity activity = new Activity(rs.getString("activity_name"));
					activity.setId(rs.getInt("id_activity"));
					
					Class c = new Class(
							rs.getInt("id"),
							rs.getString("day"),
							rs.getObject("begin_hour", LocalTime.class),
							rs.getObject("end_hour", LocalTime.class),
							teacher,
							activity
					);
					
					classes.add(c);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return classes;
	}

	public ArrayList<Class> getByTeacher(People teacher) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Class> classes = new ArrayList<Class>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select c.*, a.name activity_name \r\n"
							+ "from class c \r\n"
							+ "inner join activity a on c.id_activity = a.id \r\n"
							+ "where c.dni_teacher = ?");
			stmt.setInt(1, teacher.getDni());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activity activity = new Activity(rs.getString("activity_name"));
					activity.setId(rs.getInt("id_activity"));
					
					Class c = new Class(
							rs.getInt("id"),
							rs.getString("day"),
							rs.getObject("begin_hour", LocalTime.class),
							rs.getObject("end_hour", LocalTime.class),
							teacher,
							activity
					);
					classes.add(c);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return classes;
	}

	public boolean hasAttendants(int idClass) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean has = false;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select count(*) count from people_class where id_class = ?");
			stmt.setInt(1, idClass);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				has = rs.getInt("count") > 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return has;
	}

	public void deleteOne(int idClass) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from class where id = ?");
			stmt.setInt(1, idClass);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertOne(Class c) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into class(day, begin_hour, end_hour, dni_teacher, id_activity) values(?,?,?,?,?)");
			stmt.setString(1, c.getDay());
			stmt.setObject(2, c.getBeginHour());
			stmt.setObject(3, c.getEndHour());
			stmt.setInt(4, c.getTeacher().getDni());
			stmt.setInt(5, c.getActivity().getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteEnrollment(int dni, int idClass) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from people_class where dni_people = ? and id_class = ?");
			stmt.setInt(1, dni);
			stmt.setInt(2, idClass);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addEnrollment(int dni, int idClass) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into people_class(dni_people, id_class) values(?,?)");
			stmt.setInt(1, dni);
			stmt.setInt(2, idClass);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Class> getAvailableClasses(People p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Class> classes = new ArrayList<Class>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select c.*, a.name activity_name, t.name teacher_name, t.lastname teacher_lastname, t.phone_number teacher_phone_number \r\n"
							+ "from class c \r\n"
							+ "inner join activity a on c.id_activity = a.id \r\n"
							+ "inner join people t on c.dni_teacher = t.dni \r\n"
							+ "inner join plan_activity pa on pa.id_activity = a.id \r\n"
							+ "where pa.id_plan = ? \r\n"
							+ "and c.id not in (select id_class from people_class where dni_people = ?)");
			stmt.setInt(1, p.getPlan().getId());
			stmt.setInt(2, p.getDni());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					People teacher = new People(
							rs.getInt("dni_teacher"),
							rs.getString("teacher_name"),
							rs.getString("teacher_lastname"),
							rs.getString("teacher_phone_number")
					);
					
					Activity activity = new Activity(rs.getString("activity_name"));
					activity.setId(rs.getInt("id_activity"));
					
					Class c = new Class(
							rs.getInt("id"),
							rs.getString("day"),
							rs.getObject("begin_hour", LocalTime.class),
							rs.getObject("end_hour", LocalTime.class),
							teacher,
							activity
					);
					
					classes.add(c);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return classes;
	}
}
