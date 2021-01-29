package Jdbc0120;

import java.sql.Connection;		// <- import 'Connection'
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	
	// DB에 접속하기 위한 메소드 DBConnect()
	public static Connection DBConnect() {		
			// 1) Connection에 빨간줄뜨면 import클릭-> 'Connection'
					   // 2) DBConnect빨간줄->return ststement 클릭하면 return null;뜸
		
		// DB에 접속정보 저장을 위한 Connection타입의 변수 con 선언
		Connection con = null;
		
		// 접속할 DB의 주소정보(url : 변수)
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		// 접속할 DB의 계정정보
		String user = "SEON";
		String password = "1111";
		
			
		try {			    // ↓ oracle DB에 접속할 때
			Class.forName("oracle.jdbc.driver.OracleDriver");
								//빨간줄 -> surround try/catch 클릭
			// ojdbc6 라이브러리를 현재 소스에 적용
			
			con = DriverManager.getConnection(url,user,password);
			//      클래스                     메소드
			// ("jdbc:oracle:thin:@localhost:1521:XE,SEON,1111") 로 써도 됨
			// con은 실제 DB와 Java를 연결해주는 역할!
			
			System.out.println("DB접속 성공!");
			
		} catch (ClassNotFoundException cne) {	// 접속 실패했을 경우
			cne.printStackTrace();
			System.out.println("DB접속 실패 : 드라이버 로딩 실패!");
			
		} catch (SQLException se) {				// 접속 실패했을 경우
			se.printStackTrace();
			System.out.println("DB접속 실패 : 계정정보 확인!");
		}
		
		// printStackTrace() : 에러 발생시 경로를 찾아준다.
			
		
		// DB접속이 정상적으로 되면 접속상태(con)를 리턴해준다.
		return con;		// null아님
		
	}

}



