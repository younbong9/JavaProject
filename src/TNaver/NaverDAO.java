package TNaver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NaverDAO {
	// DAO(Data Access Object) : 데이터 접근 객체
		// - 백엔드서버, ojdbc를 사용하여 SQL을 사용할 수 있다.
		
		// DB접속을 위한 변수 con 선언
		// conn은 DB연결상태 뜻한다.
		Connection conn = null;
		
		// 쿼리문 전송을 위한 변수 pstmt 선언
		PreparedStatement pstmt = null;
		
		// 조회(select) 결과를 저장하기 위한 변수 rs 선언
		ResultSet rs = null;
		
		
		// 항목1. DB접속 메소드 connect()
		public void connect() {
			conn = DBC.DBConnect();
			// conn에 DBC클래스의 DBConnect()메소드의 
			// 리턴값(con)을 저장한다.
		}
		
		// 항목2. DB접속 해제 메소드 conClose()
		public void conClose() {
			try {
				conn.close();
				// Connection클래스의 내장메소드 
				// close()를 사용하여 접속을 해제한다.
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		
		
		// 항목3. 회원가입 메소드 memberJoin()
		public void memberJoin(NaverDTO naver) {
			String sql = "INSERT INTO NAVER VALUES(?,?,?,?,?,?,?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				// ?(물음표) 안에 값 넣기
				pstmt.setString(1, naver.getnId()); 
				pstmt.setString(2, naver.getnPw()); 
				pstmt.setString(3, naver.getnName());
				pstmt.setString(4, naver.getnBirth());
				pstmt.setString(5, naver.getnGender());
				pstmt.setString(6, naver.getnEmail());
				pstmt.setString(7, naver.getnPhone());
				
				// 7개의 정보를 다 입력 한 후 데이터베이스 실행
				// pstmt.executeUpdate();
				
				// 1. int result
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("회원가입 성공!");
				} else {
					System.out.println("회원가입 실패!");
				}
				
				// 2. boolean result2
//				boolean result2 = pstmt.execute();
//				System.out.println("성공여부 : " + result2);
//				if(!result2) {
//					System.out.println("회원가입 성공!");
//				} else {
//					System.out.println("회원가입 실패!");
//				}
				
				
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		// 항목4. 회원목록 조회하는 메소드 memberList()
		public void memberList() {
			String sql = "SELECT * FROM NAVER";
			try {
				pstmt = conn.prepareStatement(sql);
							
				rs = pstmt.executeQuery();
				// execute => boolean타입 반환 
				// executeUpdate => int타입 반환
				// executeQuery => ResultSet타입 반환
				
				int i = 1;
				while(rs.next()) {
					System.out.println(i + "번째 회원");
					System.out.println("아이디 : " + rs.getString(1));
					System.out.println("비밀번호 : " + rs.getString(2));
					System.out.println("이름 : " + rs.getString(3));
					System.out.println("생년월일 : " + rs.getString(4));
					System.out.println("성별 : " + rs.getString(5));
					System.out.println("이메일 : " + rs.getString(6));
					System.out.println("휴대전화 : " + rs.getString(7));
					System.out.println();
					i++;
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		// 항목5. 회원정보를 수정하는 메소드 memberModify()
		public void memberModify(NaverDTO naver) {
			String sql = "UPDATE NAVER SET N_PW=?, N_NAME=?,"
					+ "N_BIRTH=?, N_XY=?, N_MAIL=?, N_PHONE=? "
					+ "WHERE N_ID=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, naver.getnPw());
				pstmt.setString(2, naver.getnName());
				pstmt.setString(3, naver.getnBirth());
				pstmt.setString(4, naver.getnGender());
				pstmt.setString(5, naver.getnEmail());
				pstmt.setString(6, naver.getnPhone());
				pstmt.setString(7, naver.getnId());
				
				int result = pstmt.executeUpdate();
				
				if(result>0) {
					System.out.println("회원정보 수정성공!");
				} else {
					System.out.println("회원정보 수정실패!");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		// 항목6-1. 회원정보를 삭제하기 위해
		// 아이디와 비밀번호를 검사하는 메소드 idCheck()
		public boolean idCheck(String dId, String dPw) {
			String sql = "SELECT N_ID FROM NAVER "
						+"WHERE N_ID=? AND N_PW=?";
			boolean checkResult = false;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dId);
				pstmt.setString(2, dPw);
				
				rs = pstmt.executeQuery();
				
				// while(rs.next())
				// rs의 결과값이 1개 이기 때문에 while아닌 if를 사용
				if(rs.next()) {
					checkResult = true;
				} else {
					checkResult = false;
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return checkResult;
		}

		// 항목6. 회원정보를 삭제하는 메소드 memberDelete()
		public void memberDelete(String dId) {
			String sql = "DELETE NAVER WHERE N_ID=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dId);
				int result = pstmt.executeUpdate();
				
				if(result>0) {
					System.out.println("회원정보 삭제성공!");
				} else {
					System.out.println("회원정보 삭제실패!");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}

}
