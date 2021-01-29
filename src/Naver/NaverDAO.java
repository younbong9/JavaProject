package Naver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NaverDAO {	// NaverSQL
				// Data Access Object
	
	Connection con = null;			// import java.sql.Connection;
	// con은 DB연결상태를 뜻한다. null이면 연결되지않음, 값이 있으면 연결됨
	
	Statement stmt = null;			// 안씀, 지워도 됨
	PreparedStatement pstmt = null;	// import java.sql.PreparedStatement;
	
	
	// 1. DB접속 메소드
	public void connect() {
		con = DBCon.DBConnect();
	}
	// 2. DB접속해제 메소드
	public void conClose() {
		try {
			con.close();		// try.catch
			 // close는 새로 만든게 아니라 Connection클래스에 내장되어있는 함수
			System.out.println("DB접속 해제!");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	
	// 회원가입 조회(select) 결과 저장 변수 선언
	ResultSet rs = null;			// import java.sql.ResultSet;
	
	
	// 3. 가입정보입력 메소드insert()
	public void insert(NaverDTO dto) {
		String an = "INSERT INTO NAVER VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println("회원정보 : " + dto);
		System.out.println("DB 연결 : " + con );
			
		try {
			pstmt = con.prepareStatement(an);			// try.catch문
				// pstmt 준비단계
			pstmt.setString(1, dto.getNaverId());	// set:넣어줄때, get:불러올떄
			pstmt.setString(2, dto.getNaverPw());
			pstmt.setString(3, dto.getNaverName());
			pstmt.setInt(4, dto.getNaverBirth());
			pstmt.setString(5, dto.getNaverXy());
			pstmt.setString(6, dto.getNaverMail());
			pstmt.setString(7, dto.getNaverPhone());
				// pstmt 저장단계

			int count = pstmt.executeUpdate();
							// pstmt 실행단계
			// int일때 executeUpdate
			// boolean일때 execute
			
			if(count > 0) {
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("회원가입 실패!");
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
	
	// 4. 회원목록 조회 메소드select()
	public void select() {
		String an = "SELECT * FROM NAVER";
		try {
			pstmt = con.prepareStatement(an);	// 쿼리문을 작성하는 준비단계
			// con : Connection을 담은 변수
			// prepareStatement : 내장객체
			
			rs = pstmt.executeQuery();			// 쿼리문 실행단계
		//  rs = DB에서  "SELECT * FROM NAVER" 로 조회된 정보 전체
			
			int i=1;
			while(rs.next()) {
				System.out.println(i + "번째 가입자 정보>");
				System.out.println("아  이 디 : " + rs.getString(1));
				System.out.println("비밀번호 : " + rs.getString(2));
				System.out.println("이      름 : " + rs.getString(3));
				System.out.println("생년월일 : " + rs.getInt(4));
				System.out.println("성      별 : " + rs.getString(5));
				System.out.println("확인메일 : " + rs.getString(6));
				System.out.println("휴대번호 : " + rs.getString(7));
				i++;
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
	
	// 5. 회원정보 수정 메소드update
	public void update(NaverDTO dto) {
		String an = "UPDATE NAVER SET N_PW = ?, N_NAME = ?, N_BIRTH = ?, "
				+ "N_XY = ?, N_MAIL = ?, N_PHONE = ?"
				+ "WHERE N_ID = ?";
		
		try {
			pstmt = con.prepareStatement(an);
			
			pstmt.setString(1, dto.getNaverPw());
			pstmt.setString(2, dto.getNaverName());
			pstmt.setInt(3, dto.getNaverBirth());
			pstmt.setString(4, dto.getNaverXy());
			pstmt.setString(5, dto.getNaverMail());
			pstmt.setString(6, dto.getNaverPhone());
			pstmt.setString(7, dto.getNaverId());
			
			int count = pstmt.executeUpdate();
				// 수정된게 없으면 count는 0
			
			if(count > 0){
				System.out.println("회원정보 수정성공!");
			} else {
				System.out.println("회원정보 수정실패!");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}
	
	// 6. 회원탈퇴 메소드delete
	public void delete(String dId) {
		String an = "DELETE NAVER WHERE N_ID = ?";
						// FROM 생략해도 DB에서 가능한 문구
		try {
			pstmt = con.prepareStatement(an);
			pstmt.setString(1, dId);
			
			int count = pstmt.executeUpdate();
			
			System.out.println("count 결과 : " + count);
			
			if(count > 0) {
				System.out.println("회원탈퇴 성공!");
			} else {
				System.out.println("회원탈퇴 실패!");
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
	
/* catch문 e를 se로 바꾼 이유
이유없다. catch (SQLException e) 그냥 이렇게 써도 상관없다.
나중에	catch (ClassNotFoundException cne) 
 		catch (SQLException se) 
		catch (Exception e)
이렇게 catch 여러개 쓰일때 대비해서 그냥 바꾸자고 했던것.
		catch (ClassNotFoundException e) 
		catch (SQLException e) 
 		catch (Exception e)
셋다 e 쓰셔도 크게 상관은 없다.
*/
}
