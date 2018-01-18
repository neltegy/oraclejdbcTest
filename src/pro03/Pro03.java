package pro03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pro03 {

	public static void main(String[] args) {
        //코드작성
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "hr";
			String pw = "hr";
			conn = DriverManager.getConnection(url,id,pw);
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select employee_id "+
							      " ,last_name "+
							      " ,email "+
							      " ,job_title "+
							      " ,department_name "+
							      " ,city "+
							" from employees e "+
							    " ,jobs j "+
							    " ,departments d "+
							    " ,locations l "+
							" where e.job_id = j.job_id "+
							  " and e.department_id = d.department_id "+
							  " and d.location_id = l.location_id "+
							  " and city = 'Seattle' "+
							  " and j.job_id = 'PU_CLERK' "+
							" order by employee_id desc ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			System.out.println("employee_id  last_name \t email \t\t job_title \t department_name \t city");
			while(rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String job_title = rs.getString("job_title");
				String department_name = rs.getString("department_name");
				String city = rs.getString("city");
				
				System.out.println(employee_id+"\t"+last_name+"\t"+email+"\t"+job_title+
									"\t"+department_name+"\t\t"+city);
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
    }


}
