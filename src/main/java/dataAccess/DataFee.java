package dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.Fee;
import entities.People;

public class DataFee {

	public ArrayList<Fee> getUnpaidByPeople(People p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Fee> fees = new ArrayList<Fee>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select dni_people, due_date, amount, pay_date from fee where dni_people=? and pay_date is null");
			stmt.setInt(1, p.getDni());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Fee f = new Fee();
					f.setClient(new People(rs.getInt("dni_people")));
					f.setDueDate(rs.getObject("due_date", LocalDate.class));
					f.setAmount(rs.getDouble("amount"));
					f.setPayDate(rs.getObject("pay_date", LocalDate.class));
					fees.add(f);
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
		return fees;
	}

	public Fee getOne(int dni, LocalDate dueDate) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Fee f = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select dni_people, due_date, amount, pay_date from fee where dni_people=? and due_date=?");
			stmt.setInt(1, dni);
			stmt.setObject(2, dueDate);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				f = new Fee();
				f.setClient(new People(rs.getInt("dni_people")));
				f.setDueDate(rs.getObject("due_date", LocalDate.class));
				f.setAmount(rs.getDouble("amount"));
				f.setPayDate(rs.getObject("pay_date", LocalDate.class));
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
		return f;
	}

	public void insertOne(Fee f) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into fee(dni_people, due_date, amount, pay_date) values(?,?,?,?)");
			stmt.setInt(1, f.getClient().getDni());
			stmt.setObject(2, f.getDueDate());
			stmt.setDouble(3, f.getAmount());
			stmt.setObject(4, f.getPayDate());
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

	public void updatePayDate(int dni, LocalDate dueDate) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("update fee set pay_date = curdate() where dni_people=? and due_date=?");
			stmt.setInt(1, dni);
			stmt.setObject(2, dueDate);
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
