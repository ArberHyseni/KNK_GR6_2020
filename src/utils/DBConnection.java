package utils;

import java.sql.*;

public class DBConnection {

	private static Connection dbConnection;
	private final static String host = "localhost:3306";
	private final static String dbName = "GameDB";
	private final static String username = "root";
	private final static String password = "AB1240688731ab.";

	public static Connection getConnection() {
		if (dbConnection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbName + "?allowMultiQueries=TRUE&useUnicode=TRUE&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dbConnection;
	}

}