package dogShelter2;

import java.sql.*;

public class ShelterDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void connect() {
		conn = DBcon.DBConnect();
	}
	
	public void conClose() {
		try {
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void memberJoin(ShelterDTO shelter) {
		String sql = "INSERT INTO SHELTER VALUES(?,?,?,?,?,?)";
		
		System.out.println("회원정보 : "+shelter);
		System.out.println("DB연결 : "+conn);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setNString(1, shelter.getsId());
			pstmt.setNString(2, shelter.getsPw());
			pstmt.setNString(3, shelter.getsName());
			pstmt.setNString(4, shelter.getsAddr());
			pstmt.setNString(5, shelter.getsEmail());
			pstmt.setNString(6, shelter.getsPhone());
			
			boolean result2 = pstmt.execute();
			if (!result2) {
				System.out.println("회원등록 성공");
			} else {
				System.out.println("회원등록 실패");
			}
		}catch (SQLException se){
			se.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public boolean idCheck(String sId, String sPw) {
		String sql = "SELECT SID FROM SHELTER WHERE SID=? AND SPW=?";
		boolean checkResult = false;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setNString(1, sId);
			pstmt.setNString(2, sPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkResult = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
		return checkResult;
	}

	public void donation(int money) {
		
	}

	public void dogListByBreed(String breed) {
		String sql = "SELECT * FROM SHELTER WHERE BREED = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, breed);
			rs = pstmt.executeQuery(); 
			
			int i=0;
			while(rs.next()) {	
				
				System.out.println(i+"번째 강아지 정보");
				System.out.println("공고번호 : " + rs.getString(1));
				System.out.println("이름 : " + rs.getString(2));
				System.out.println("추정나이 : " + rs.getString(3));
				System.out.println("품종 : " + rs.getString(4));
				System.out.println("성별 : " + rs.getString(5));
				System.out.println("접수일자 : " + rs.getString(6));
				System.out.println();
				i++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
	}

	public void dogList() {
		String sql = "SELECT * FROM DOG";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			int i=0;
			while(rs.next()) {	
				
				System.out.println(i+"번째 강아지 정보");
				System.out.println("공고번호 : " + rs.getString(1));
				System.out.println("이름 : " + rs.getString(2));
				System.out.println("추정나이 : " + rs.getString(3));
				System.out.println("품종 : " + rs.getString(4));
				System.out.println("성별 : " + rs.getString(5));
				System.out.println("접수일자 : " + rs.getString(6));
				System.out.println();
				i++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
	}

	public void addDog(DogDTO dog) {
		String sql = "INSERT INTO DOG VALUES(?,?,?,?,?,?)";
		
		System.out.println("회원정보 : "+dog);
		System.out.println("DB연결 : "+conn);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setNString(1, dog.getdNo());
			pstmt.setNString(2, dog.getdName());
			pstmt.setInt(3, dog.getdAge());
			pstmt.setNString(4, dog.getdSex());
			pstmt.setNString(5, dog.getdBreed());
			pstmt.setNString(6, dog.getdRegitDate());
			
			boolean result2 = pstmt.execute();
			if (!result2) {
				System.out.println("유기견등록 성공");
			}else {
				System.out.println("유기견등록 실패");
			}
		}catch (SQLException se){
			se.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
	}

	public boolean dogCheck(String modDNo) {
		String sql = "SELECT DNO FROM DOG WHERE DNO = ?";
		boolean checkResult = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modDNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkResult = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return checkResult;
	}

	public void modifyDog(DogDTO dog) {
		String sql = "UPDATE DOG SET DNO=?,DNAME=?, DAGE=?, DBREED=?, DSEX=?, DREGITDATE=? WHERE DNO=?";
		
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setNString(1, dog.getdNo());
			pstmt.setNString(2, dog.getdName());
			pstmt.setInt(3, dog.getdAge());
			pstmt.setNString(4, dog.getdBreed());
			pstmt.setNString(5, dog.getdSex());
			pstmt.setNString(6, dog.getdRegitDate());
			
			int result = pstmt.executeUpdate();
			if (result>0) {
				System.out.println("학생정보 수정 성공");
			}else {
				System.out.println("학생정보 수정 실패");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}

	public void deleteDog(String delDNo) {
		String sql = "DELETE DOG WHERE DNO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setNString(1, delDNo);
			
			int result = pstmt.executeUpdate();
			if(result>0) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
	}
}