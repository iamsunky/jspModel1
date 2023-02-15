package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;

public class BbsDao {
	private static BbsDao dao = null;

	private BbsDao() {
		// TODO Auto-generated constructor stub
	}

	public static BbsDao getInstance() {
		if (dao == null) {
			dao = new BbsDao();
		}
		return dao;
	}

	// 게시판 리스트
	public List<BbsDto> getBbsList() {
		List<BbsDto> list = new ArrayList<BbsDto>();

		String sql = "SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT FROM BBS ORDER BY 3 DESC, 4 ASC ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new BbsDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	} // end of list

	// 게시판 리스트
	public List<BbsDto> getBbsSearchList(String choice, String search) {
		List<BbsDto> list = new ArrayList<BbsDto>();

		String sql = "SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT FROM BBS";

		String searchSql = "";
		if (choice.equals("title")) {
			searchSql = " WHERE TITLE LIKE '%" + search + "%'";
		} else if (choice.equals("content")) {
			searchSql = " WHERE CONTENT LIKE '%" + search + "%'";
		} else if (choice.equals("writer")) {
			searchSql = " WHERE ID ='" + search + "'";
		}

		sql += searchSql;

		sql += " ORDER BY 3 DESC, 4 ASC ";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new BbsDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	} // end of list
	
	public List<BbsDto> getBbsPageList(String choice, String search, int pageNumber) {
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		String sql = "select seq, id, ref, step, depth, title, content, wdate, del, readcount "
				+ "from "
				+ "(select row_number()over(order by ref desc, step asc) as rnum,"
				+ "seq, id, ref, step, depth, title, content, wdate, del, readcount "
				+ "from bbs ";
		
		String searchSql = "";
		if (choice.equals("title")) {
			searchSql = " WHERE TITLE LIKE '%" + search + "%' ";
		} else if (choice.equals("content")) {
			searchSql = " WHERE CONTENT LIKE '%" + search + "%' ";
		} else if (choice.equals("writer")) {
			searchSql = " WHERE ID ='" + search + "'";
		}
		
		sql += searchSql;
		
		sql +=  "order by ref desc, step asc) a "
				+ "where rnum between ? and ?";
		
		
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		// pageNumber
		int start, end;
		start = 1 + 10 * pageNumber;
		end = 10 + 10 * pageNumber;
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				list.add(new BbsDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	} // end of list

	// 총 게시글 수
	public int getAllBbs(String choice, String search) {
		String sql = "SELECT COUNT(*) FROM BBS";

		String searchSql = "";
		if (choice.equals("title")) {
			searchSql = " WHERE TITLE LIKE '%" + search + "%'";
		} else if (choice.equals("content")) {
			searchSql = " WHERE CONTENT LIKE '%" + search + "%'";
		} else if (choice.equals("writer")) {
			searchSql = " WHERE ID ='" + search + "'";
		}

		sql += searchSql;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, rs);
		}

		return count;
	}

	// 글 작성하기
	public boolean write(String id, String title, String content) {
		int count = 0;

		String sql = "INSERT INTO BBS(ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT) VALUES(?, (SELECT IFNULL(MAX(REF), 0)+1 FROM BBS B), 0, 0, ?, ?, NOW(), 0, 0)";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, content);

			System.out.println(sql);

			count = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count > 0 ? true : false;
	}
	
	// 게시글 상세
	public BbsDto getBbs(int seq) {
		BbsDto dto = null;
		String sql = "SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT FROM BBS WHERE SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			
			
			if(rs.next()) {
				dto = new BbsDto(rs.getInt(1)
											, rs.getString(2)
											, rs.getInt(3)
											, rs.getInt(4)
											, rs.getInt(5)
											, rs.getString(6)
											, rs.getString(7)
											, rs.getString(8)
											, rs.getInt(9)
											, rs.getInt(10)
											);
				System.out.println(dto.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
	}
	
	// 답글
	public boolean answer(int seq, BbsDto dto) {
		// update
		String sql1 = " update bbs "
				+ " set step=step+1 "
				+ " where ref=(select ref from (select ref from bbs a where seq=?) A) "
				+ "	  and step>(select step from (select step from bbs b where seq=?) B) ";
		// insert
		String sql2 = " insert into bbs(id, ref, step, depth, title, content, wdate, del, readcount) "
				+ " values(?, "
				+ "		(select ref from bbs a where seq=?), "
				+ "		(select step from bbs b where seq=?)+1, "
				+ "		(select depth from bbs c where seq=?)+1, "
				+ "		?, ?, now(), 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count1 = 0, count2= 0;
		
		
		try {
			conn = DBConnection.getConnection();
			// auto commit을 비활성화
			conn.setAutoCommit(false);
			
			// update
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			
			count1 = psmt.executeUpdate();
			
			// psmt 초기화
			psmt.clearParameters();
			
			// insert
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, dto.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			
			count2 = psmt.executeUpdate();
			
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBClose.close(conn, psmt, null);
		}
		
		return count2 > 0 ? true : false;
		
	} // answer
	
	// delete
	public boolean delete(int seq) {
		String sql = "UPDATE BBS SET DEL = 1 WHERE SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}
	
	// update
	public boolean update(int seq, String title, String content) {
		String sql = "UPDATE BBS SET TITLE = ?, CONTENT = ? WHERE SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			
			count = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}
	
	// 조회수 증가
	public void incHit(int seq) {
		String sql = "UPDATE BBS SET READCOUNT = READCOUNT+1 WHERE SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, seq);
			System.out.println(sql);
			
			psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBClose.close(conn, psmt, null);
		}
	}

}// end of class