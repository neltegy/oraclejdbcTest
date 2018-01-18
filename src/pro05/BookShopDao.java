package pro05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pro04.MemberVo;

public class BookShopDao {
	
	public void insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "webdb";
			String pw = "webdb";
			conn = DriverManager.getConnection(url, id, pw);
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO bookshop VALUES (seq_bookshop_id.nextval, ?, null , null ,? , ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthorName());
			pstmt.setString(3, "1");

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 저장완료");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
	}
	
	public void rent(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "webdb";
			String pw = "webdb";
			conn = DriverManager.getConnection(url,id,pw);
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update bookshop set state_code = '0' where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			
			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 변경완료");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
	}
	
	public List<BookVo> getListAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BookVo> bookvolist = new ArrayList<BookVo>();
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "webdb";
			String pw = "webdb";
			conn = DriverManager.getConnection(url,id,pw);
			// 3. SQL문 준비 / 바인딩 / 실행
				String query = "select title "+
								      " ,author_name "+
								      " ,state_code "+
								" from bookshop ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				BookVo bvo = new BookVo();
				
				bvo.setTitle(rs.getString("title"));
				bvo.setAuthorName(rs.getString("author_name"));
				bvo.setStatecode(rs.getString("state_code"));
				
				bookvolist.add(bvo);
				
				//authorList.toString();
				//System.out.println(authorId+" "+authorName+" "+authorDesc);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		return bookvolist;
	}
	
	
}
