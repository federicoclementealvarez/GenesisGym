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
		return(plans);
	}

	public void insertOne(Plan p, String[] activityIds) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into plan(name, rate) values(?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, p.getName());
			stmt.setDouble(2, p.getRate());
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				int idPlan = rs.getInt(1);
				
				PreparedStatement stmtRel = DbConnector.getInstancia().getConn()
						.prepareStatement("insert into plan_activity(id_plan, id_activity) values(?,?)");
				for (String idAct : activityIds) {
					stmtRel.setInt(1, idPlan);
					stmtRel.setInt(2, Integer.parseInt(idAct));
					stmtRel.executeUpdate();
				}
				stmtRel.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) rs.close();
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
					.prepareStatement("select count(*) count from people where id_plan = ?");
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
					.prepareStatement("delete from plan_activity where id_plan = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();

			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from plan where id = ?");
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
