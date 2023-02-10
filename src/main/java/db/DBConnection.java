package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static void initConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Success");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		// DB와 Java를 연결하는 작업
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
			System.out.println("Connection Success");
		} catch (SQLException e) {
			System.out.println("DB를 연결하지 못했습니다.");
		}
		return conn;
	}
	
} // end of main
