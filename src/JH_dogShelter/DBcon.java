package JH_dogShelter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
	
	public static Connection DBConnect() {
		
		Connection con = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		
		String user = "SEON";
		String password = "1111";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url,user,password);
			
			System.out.println("DB접속 성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB접속 실패: 드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("db접속 실패: 계정정보확인");
		}
		return con;
}
}
