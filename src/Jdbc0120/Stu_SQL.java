package Jdbc0120;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stu_SQL {
	
	// DB접속을 위한 변수con 선언
	Connection con = null;	// Connection타입의 변수con 선언	
	
	
	// 쿼리문 전송을 위한 변수 선언(->DB에 전송)
	Statement stmt = null;
	PreparedStatement pstmt = null;	// PreparedStatement : ?를 문자로 인식
	
		
	// 조회(select) 결과를 저장하기 위한 변수 선언
	ResultSet rs = null;		// DB에서의 select 결과를 rs변수에 저장
	
		
	// DB접속을 위한 메소드
	public void connect() {
		con = DBCon.DBConnect();	// DBConnect : DB에 접속하기 위한 메소드
		// DBCon클래스의 DBConnect()메소드의 리턴값(con)을
		// con에 담겠다.
		// con(파랑) <= con = DriverManager.getConnection(url,user,password);
	}
		
	
	// DB접속 해제
	public void conClose() {
		
		try {
			con.close();		// DB접속 해제 : con.colse();-> 빨간줄-> try/catch
			System.out.println("DB접속 해제!");
		} catch (SQLException se) {
			se.printStackTrace();
		}		
	}

	

	// 학생등록을 위한 메소드insert()
	// insert(StuDTO stu) : 파라미터로 StuDTO의 내용을 가져온다.
	public void insert(StuDTO stu) {
		String sql = "INSERT INTO STUDTO VALUES(?, ?)";
		
		// stu(학생정보)값과 con(DB연결)값 확인
		System.out.println("학생정보 : " + stu);
		System.out.println("DB연결 : " + con);
		
		// stmt = con.createStatement(); 어제는 이렇게 했다....
		
		try {
			pstmt = con.prepareStatement(sql);
			
			// 숫자는 물음표 순서대로, 물음표 안에 들어갈 내용
			pstmt.setString(1, stu.getStuName()); // 1은 물음표 첫번째
			pstmt.setInt(2,  stu.getStuAge());	// 2는 물음표 두번쨰
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("학생등록 성공!");				
			} else {
				System.out.println("학생등록 실패!");
			}
			
		} catch (SQLException se) {	
			se.printStackTrace();
		} finally {
			// try : 정상적으로 작동할 때
			// catch : 오류가 발생할 때(예외처리가 발생할 때)
			// Exception : 예외처리
			// finally : 정상적으로 작동하거나 예외처리 발생해도
			//			  상관없이 무조건 작동
			// try-catch-finally 세트로 움직임
			
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	
	// 학생정보를 조회하는 메소드select()
	public void select() {
		String sql = "SELECT * FROM STUDTO";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 1;
			while(rs.next()) {	// 조회된 데이터의 갯수만큼 반복문 실행
								// Cardinality(Tuple, record) 데이터의 수만큼
				System.out.println(i + "번째 학생 정보>");
				System.out.println("이름 : " + rs.getString(1));	// 첫번째컬럼
				System.out.println("나이 : " + rs.getInt(2));		// 두번째컬럼
											// rs.getInt(2) : 두번째컬럼값을 가져옴
				System.out.println();
				i++;
			}
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	
	// 학생정보를 수정하는 메소드update()
	// update(StuDTO stu) : 메소드 안에 StuDTO정보를 가지고 있음
	public void update(StuDTO stu) {
		String sql = "UPDATE STUDTO SET STUAGE = ? "
				   + "WHERE STUNAME = ?";
							// STUNAME은 PK인 컬럼으로 지정한다.
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  stu.getStuAge());		// 바꾸고자 하는 나이
			pstmt.setString(2,  stu.getStuName());	// 바꾸고자 하는 이름
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("학생정보 수정성공!");
			} else {
				System.out.println("학생정보 수정실패!");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} 
	}


	
	// 학생정보를 삭제하는 메소드delete()
	// delete(String dName) : 메소드 안에 String타입의 dName정보를 가지고 있다.
	public void delete(String dName) {
		String sql = "DELETE STUDTO WHERE STUNAME = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dName);		// 첫번째 물음표에 dName을 넣어준다.
			
			int count = pstmt.executeUpdate();
			
			System.out.println("count 결과 :" + count);
			
			if(count > 0) {
				System.out.println("학생정보 삭제성공!");
			} else {
				System.out.println("학생정보 삭제실패!");
			}
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	
	
}
