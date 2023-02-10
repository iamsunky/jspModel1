package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {
	public static void close(Connection conn, Statement psmt, ResultSet rs) {
		try {
			if(conn != null) {
				conn.close();
			} 
			if(psmt != null) {
				psmt.close();
			} 
			if(rs != null) {
				rs.close();
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
