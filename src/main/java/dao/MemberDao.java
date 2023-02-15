package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {

	private static MemberDao dao = null;
	
	private MemberDao() {
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	public boolean getId(String id) {
		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean findid = false;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/3 getId success");
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				findid = true;
			} else findid = false;
			
			System.out.println("3/3 getId success");
			
		} catch (SQLException e) {
			System.out.println("getId() fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return findid;
		
	} // getId
	
	public boolean addMember(MemberDto dto) {
		
		String sql = "INSERT INTO MEMBER(ID, PWD, NAME, EMAIL, AUTH) VALUES(?,?,?,?,3)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMember success");
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			System.out.println("2/3 addMember success");
			
			count = psmt.executeUpdate();
			System.out.println("count: " + count);
			System.out.println("3/3 addMember success");
		} catch (SQLException e) {
			System.out.println("addMember fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
		
	} // addMember()
	
	/*
	public boolean login(String id, String pw) {
		boolean isTrue = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT PWD FROM MEMBER WHERE ID = ?";
		
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			// ?에 값을 집에 넣기 위한 메소드
			psmt.setString(1, id);
			
			// 쿼리문을 실행했을 때의 결과값
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String pwd = rs.getString(1);
				System.out.println("pwd :" + rs.getString(1));
				if(pwd.equals(pw)) {
					isTrue = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("에러남");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}

		return isTrue;
		
	} // end of login
	*/
	
	// 로그인
	public MemberDto login(String id, String pwd) {
		String sql = "SELECT ID, NAME, EMAIL, AUTH FROM MEMBER WHERE ID = ? AND PWD = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto mem = null;
		
		try {
			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String _id = rs.getString(1);
				String _name = rs.getString(2);
				String _email = rs.getString(3);
				int _auth = rs.getInt(4);
				
				mem = new MemberDto(_id, null, _name, _email, _auth);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return mem;
	} // login

} // MemberDAO