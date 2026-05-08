package dataAccess;
import java.sql.*;

public class DbConnector {

	private static DbConnector instanciaConector;

	private String driver="com.mysql.cj.jdbc.Driver";
	private String host = System.getenv("MYSQLHOST") != null ? System.getenv("MYSQLHOST") : "localhost";
	private String port = System.getenv("MYSQLPORT") != null ? System.getenv("MYSQLPORT") : "3306";
	private String user = System.getenv("MYSQLUSER") != null ? System.getenv("MYSQLUSER") : "fedeuser";
	private String password = System.getenv("MYSQLPASSWORD") != null ? System.getenv("MYSQLPASSWORD") : "Password123";
	private String db = System.getenv("MYSQLDATABASE") != null ? System.getenv("MYSQLDATABASE") : "genesys_gym";
	private String options="";
	//private String options="?useLegacyDatetimeCode=false&ServerTimezone=Asia/Hong_Kong";
	private int conectados=0;
	private Connection conn=null;
	
	private DbConnector() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static DbConnector getInstancia() {
		if (instanciaConector == null) {
			instanciaConector = new DbConnector();
		}
		return instanciaConector;
	}
	
	public Connection getConn() {
		try {
			if(conn==null || conn.isClosed()) {
				conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+options, user, password);
				conectados=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
