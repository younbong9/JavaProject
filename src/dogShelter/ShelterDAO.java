package dogShelter; // server

import java.sql.*;

public class ShelterDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// DB 연결
	public void connect() {
		conn = DBcon.DBConnect();
	}

	// DB 해제
	public void conClose() {
		try {
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 1. 로그인
	public boolean logIn(String sId, String sPw) {
		String sql = "SELECT SID FROM SHELTER WHERE SID = ? AND SPW = ?";
		boolean ckeckLogin = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, sPw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ckeckLogin = true;
				System.out.println();
				System.out.println("[ (≧∇≦)/  로그인 성공! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 로그인 실패! ]");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return ckeckLogin;
	}

	// 1-2-1.전체조회
	public void dogList() {
		String sql = "SELECT * FROM DOG";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int i = 1;
			while (rs.next()) {
				System.out.println();
				System.out.println(i + "번째 유기견");
				System.out.println("공고번호 : " + rs.getString(1));
				System.out.println("접수일자 : " + rs.getString(2));
				System.out.println("품      종 : " + rs.getString(3));
				System.out.println("성      별 : " + rs.getString(4));
				System.out.println("특      징 : " + rs.getString(5));
				System.out.println("추정나이 : " + rs.getString(6));
				System.out.println();
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

	// 1-2-2.품종별조회
	public void dogListB(String searchBreed) {
		String sql = "SELECT * FROM DOG WHERE SBREED = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchBreed);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println("공고번호 : " + rs.getString(1));
				System.out.println("접수일자 : " + rs.getString(2));
				System.out.println("품종 : " + rs.getString(3));
				System.out.println("성별 : " + rs.getString(4));
				System.out.println("특징 : " + rs.getString(5));
				System.out.println("추정나이 : " + rs.getString(6));
				System.out.println();
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

	// 1-2-3.성별조회
	public void dogListG(String searchGender) {
		String sql = "SELECT * FROM DOG WHERE SGENDER = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchGender);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println("공고번호 :" + rs.getString(1));
				System.out.println("접수일자 : " + rs.getString(2));
				System.out.println("품종 : " + rs.getString(3));
				System.out.println("성별 : " + rs.getString(4));
				System.out.println("특징 : " + rs.getString(5));
				System.out.println("추정나이 : " + rs.getString(6));
				System.out.println();
			}

		} catch (SQLException se) {

			se.printStackTrace();
		}
	}

	// 1-2. 후원하기
	public void spon(String dId, int money) {
		String sql = "INSERT INTO DONATE VALUES(?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dId);
			pstmt.setInt(2, money);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println();
				System.out.println("[ (≧∇≦)/   후원해 주셔서 감사합니다! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 후원 실패 ]");
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

	// 1-2. 후원금 더하는 메소드sumDon()
	public void sumDon(String dId, int money) {
		String sql = "UPDATE DONATE SET DONATION = DONATION + ? "
				+ "WHERE DID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, dId);
			
			pstmt.execute();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}	
	}

	// 1-3. 입양
	public void dogAdop(String sNum, String sBreed, String sGender) {
		String sql = "DELETE FROM DOG WHERE SNUM = ? AND SBREED = ? AND SGENDER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sNum);
			pstmt.setString(2, sBreed);
			pstmt.setString(3, sGender);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ 입양 등록이 완료되었습니다. (≧∇≦)/  ]");
				System.out.println("[ 정확한 절차는 관리자와의 상담이 필요합니다! ]");
			} else {
				System.out.println();
				System.out.println("[ 입양 등록이 완료되지 않았습니다. (-_-。) ]");
				System.out.println("[ 정보를 정확히 입력해주세요! ]");
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

	// 2. 회원가입
	public void memberJoin(String sId, String sPw, String sName, String sAddr, String sPhone, String sEmail) {
		String sql = "INSERT INTO SHELTER VALUES(?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, sPw);
			pstmt.setString(3, sName);
			pstmt.setString(4, sAddr);
			pstmt.setString(5, sPhone);
			pstmt.setString(6, sEmail);			

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ (/^▽^)/ 회원가입 성공! (/^▽^)/ ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 회원가입 실패! (-_-。) ]");
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

	// // 3-1-1.유기견 정보등록
	public void dogReg(String sNum, String sDate, String sBreed, String sGender, String sChar, String sEstiAge) {
		String sql = "INSERT INTO DOG VALUES(?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sNum);
			pstmt.setString(2, sDate);
			pstmt.setString(3, sBreed);
			pstmt.setString(4, sGender);
			pstmt.setString(5, sChar);
			pstmt.setString(6, sEstiAge);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ (≧∇≦)/  등록 성공! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 등록 실패! ]");
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

	// 3-1-2.유기견 정보수정
	public void dogModify(String sNum, String sDate, String sBreed, String sGender, String sChar, String sEstiAge) {
		String sql = "UPDATE DOG SET SDATE = ?, SBREED = ?, SGENDER = ?, SCHAR = ?, SESTIAGE = ? " + "WHERE SNUM = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sDate);
			pstmt.setString(2, sBreed);
			pstmt.setString(3, sGender);
			pstmt.setString(4, sChar);
			pstmt.setString(5, sEstiAge);
			pstmt.setString(6, sNum);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ (≧∇≦)/  정보삭제 성공! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 정보삭제 실패! ]");
				System.out.println("[ 정보를 정확히 입력해주세요! ]");
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

	// 3-1-3.유기견 정보삭제
	public void dogDel(String sNum) {
		String sql = "DELETE FROM DOG WHERE SNUM = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sNum);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ (≧∇≦)/  정보삭제 성공! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 정보삭제 실패! ]");
				System.out.println("[ 정보를 정확히 입력해주세요! ]");
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

	// 3-2-1. 회원조회
	public void searchMember(String searchId) {
		String sql = "SELECT * FROM SHELTER WHERE SID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println();
				System.out.println("아  이 디 : " + rs.getNString(1));
				System.out.println("비밀 번호 : " + rs.getNString(2));
				System.out.println("이      름 : " + rs.getNString(3));
				System.out.println("주      소 : " + rs.getNString(4));				
				System.out.println("연   락  처 : " + rs.getNString(5));
				System.out.println("이   메  일: " + rs.getNString(6));
			} else {
				System.out.println();
				System.out.println("[ 존재하지 않는 회원입니다! ]");
				System.out.println();
				System.out.println();
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

	// 3-2-2. 회원정보수정시 아이디체크
	public boolean memCheck(String mId) {
		String sql = "SELECT SID FROM SHELTER WHERE SID = ?";
		boolean checkResult = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return checkResult;
	}

	// 3-2-2. 회원정보수정
	public void updateMember(ShelterDTO shelter) {
		String sql = "UPDATE SHELTER SET SPW = ?, SNAME = ?, SADDR = ?, SPHONE = ?, SEMAIL = ? "
				+ "WHERE SID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shelter.getsPw());
			pstmt.setString(2, shelter.getsName());
			pstmt.setString(3, shelter.getsAddr());
			pstmt.setString(4, shelter.getsPhone());
			pstmt.setString(5, shelter.getsEmail());
			pstmt.setString(6, shelter.getsId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ (≧∇≦)/  회원정보 변경 완료! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 회원정보 변경 실패! ]");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 3-2-3. 회원정보삭제시 아이디체크
	public boolean memCheck2(String dId) {
		String sql = "SELECT SID FROM SHELTER WHERE SID = ?";
		boolean checkResult = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return checkResult;
	}

	// 3-2-3. 회원정보삭제
	public void deleteMember(String dId) {
		String sql = "DELETE FROM SHELTER WHERE SID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println();
				System.out.println("[ (≧∇≦)/  회원정보 삭제 성공! ]");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 회원정보 삭제 실패! ]");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 3-3. 후원금 명단
	public void don(String donId) {
		String sql = "SELECT * FROM DONATE WHERE DID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, donId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println();
				System.out.println("후원자 조회 결과입니다.");
				System.out.println("아이디 : " + rs.getNString(1));
				System.out.println("후원금 : " + rs.getNString(2) + "원");
			} else {
				System.out.println();
				System.out.println("[ (-_-。) 후원금이 존재하지 않습니다! ]");
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
