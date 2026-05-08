package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Activity;

public class DataActivity {

	public ArrayList<Activity> getAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select id, name, description, teacheable from activity");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activity a = new Activity(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getBoolean("teacheable")
					);
					activities.add(a);
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
		return activities;
	}
	
	public ArrayList<Activity> getAllTeacheable() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select id, name, description, teacheable from activity where teacheable = True");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activity a = new Activity(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getBoolean("teacheable")
					);
					activities.add(a);
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
		return activities;
	}
	
	public Activity getOne(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Activity a = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select id, name, description, teacheable from activity where id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				a = new Activity(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getBoolean("teacheable")
				);
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
		return a;
	}

	public ArrayList<Activity> getByPlan(int idPlan) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select a.* from activity a "
							+ "inner join plan_activity pa on a.id = pa.id_activity "
							+ "where pa.id_plan = ?");
			stmt.setInt(1, idPlan);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activity a = new Activity(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getBoolean("teacheable")
					);
					activities.add(a);
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
		return activities;
	}

	public ArrayList<Activity> getNonTeacheable() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select id, name, description, teacheable from activity where teacheable = False");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Activity a = new Activity(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getBoolean("teacheable")
					);
					activities.add(a);
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
		return activities;
	}
}
