package JH_dogShelter;

import java.sql.*;


public class ShelterDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 접속 메소드
	public void connect() {
		conn = DBcon.DBConnect();
	}
	
	// 접속 해제 메소드
	public void conClose() {
		try {
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 로그인시 아이디와 비밀번호가 일치한지 확인하는 메소드
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
	
	// 1-2. 입양
		public void dogAdop(int sNum, String sBreed, String sGender) {
			String sql = "DELETE FROM DOG WHERE DNO = ? AND DBREED = ? AND DGENDER = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sNum);
				pstmt.setString(2, sBreed);
				pstmt.setString(3, sGender);

				int result = pstmt.executeUpdate();
				if(result > 0) {
					System.out.println("입양 등록 성공!");
					System.out.println("정확한 절차는 관리자와의 상담이 필요합니다!");
				} else {
					System.out.println("입양 등록 실패!");
					System.out.println("정보를 정확히 입력해주세요!");
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
		
	// 회원가입을 위한 메소드 memberJoin()
	public void memberJoin(ShelterDTO shelter) {
		String sql = "INSERT INTO SHELTER VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shelter.getsId());
			pstmt.setString(2, shelter.getsPw());
			pstmt.setString(3, shelter.getsName());
			pstmt.setString(4, shelter.getsAddr());
			pstmt.setString(5, shelter.getsPhone());
			pstmt.setString(6, shelter.getsEmail());
			pstmt.setInt(7, shelter.getSponsor());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("회원가입 실패!");
			}
			
		} catch (SQLException se) {
			
			se.printStackTrace();
		}
	}
	
	
	
	

	// 전체 개 조회 메소드 selectDog()
	public void selectDog(ShelterDTO2 dog) {
		String sql = "SELECT * FROM DOG";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("공고번호 :" + rs.getString(1));
		    	System.out.println("이름 : " + rs.getString(2));
		    	System.out.println("나이 : " + rs.getString(3));
		    	System.out.println("품종 : " + rs.getString(4));
		    	System.out.println("성별 : " + rs.getString(5));
		    	System.out.println("접수일자 : " + rs.getString(6));
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
	
	
	// 개 품종별 조회
		public void dogListB(String searchBreed) {
			String sql = "SELECT * FROM DOG WHERE DBREED = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchBreed);
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	System.out.println("공고번호 :" + rs.getString(1));
			    	System.out.println("이름 : " + rs.getString(2));
			    	System.out.println("나이 : " + rs.getString(3));
			    	System.out.println("품종 : " + rs.getString(4));
			    	System.out.println("성별 : " + rs.getString(5));
			    	System.out.println("접수날짜 : " + rs.getString(6));
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
		
		// 개 성별로 조회
		public void dogListG(String searchGender) {
			String sql = "SELECT * FROM DOG WHERE DGENDER = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchGender);
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	System.out.println("공고번호 :" + rs.getString(1));
			    	System.out.println("이름 : " + rs.getString(2));
			    	System.out.println("나이 : " + rs.getString(3));
			    	System.out.println("품종 : " + rs.getString(4));
			    	System.out.println("성별 : " + rs.getString(5));
			    	System.out.println("접수날짜 : " + rs.getString(6));
			    	System.out.println();
			    }
				
			} catch (SQLException se) {
				
				se.printStackTrace();
			} 
		}
		
	// 유기견 등록을 위한 메소드 register()
	public void register(ShelterDTO2 dog) {
		String sql = "INSERT INTO DOG (DNO, DNAME, DAGE, DBREED, DGENDER, D_DATE)"
				+ "VALUES(DOG_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dog.getdName());
			pstmt.setString(2, dog.getdAge());
			pstmt.setString(3, dog.getdBreed());
			pstmt.setString(4, dog.getdGender());
			pstmt.setString(5, dog.getdDate());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				System.out.println(result);
				System.out.println("유기견 등록 성공");
			} else {
				System.out.println("유기견 등록 실패");
			}
		
		} catch (SQLException se) {
			
			se.printStackTrace();
		}	// end catch
		
	}	// end register

	
	// 유기견 목록 삭제를 위한 메소드 deleteDog()
	public void deleteDog(ShelterDTO2 dog) {
		String sql = "DELETE DOG WHERE DNO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dog.getdNo());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("삭제 완료!");
			} else {
				System.out.println("삭제 실패!");
			}
			
		} catch (SQLException se) {
			
			se.printStackTrace();
		}
	}	// end deleteDog

	// 유기견 목록 수정을 위한 메소드 updateDog()
	public void updateDog(ShelterDTO2 dog) {
		String sql = "UPDATE DOG SET DNAME=?, DAGE=?, DBREED=?, DGENDER=?, D_DATE=? WHERE DNO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dog.getdName());
			pstmt.setString(2, dog.getdAge());
			pstmt.setString(3, dog.getdBreed());
			pstmt.setString(4, dog.getdGender());
			pstmt.setString(5, dog.getdDate());
			pstmt.setInt(6, dog.getdNo());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("수정 완료!");
			} else {
				System.out.println("수정 실패!");
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
	
	// 모든 회원정보를 조회하기 위한 메소드 memberList()
	public void memberList(ShelterDTO shelter) {
		String sql = "SELECT * FROM SHELTER";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int i = 1;
			
			while(rs.next()) {
				System.out.println(i + "번째 회원정보");
				System.out.println("Id : " + rs.getString(1));
				System.out.println("Pw : " + rs.getString(2));
				System.out.println("이름 : " + rs.getString(3));
				System.out.println("주소 : " + rs.getString(4));
				System.out.println("연락처 : " + rs.getString(5));
				System.out.println("email : " + rs.getString(6));
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

	// 아이디 존재여부 체크하는 메소드
	public boolean memCheck(String delId) {
		String sql = "SELECT SID FROM SHELTER WHERE SID = ?";
		boolean checkResult = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, delId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
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
		
		return checkResult;
	}

	public boolean memCheck2(String uId) {
		String sql = "SELECT SID FROM SHELTER WHERE SID = ?";
		boolean checkResult = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
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
		
		return checkResult;
	}
	
	// 회원 아이디를 삭제하기 위한 메소드
	public void deleteId(String delId) {
		String sql = "DELETE SHELTER WHERE SID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, delId);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("삭제 실패!");
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

	// 관리자 메뉴에서 회원 정보수정을 위한 메소드
	public void updateShelter(ShelterDTO shelter) {
		String sql = "UPDATE SHELTER SET SPW=?, SNAME=?, SADDR=?, SPHONE=?, SEMAIL=? WHERE SID=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shelter.getsPw());
			pstmt.setString(2, shelter.getsName());
			pstmt.setString(3, shelter.getsAddr());
			pstmt.setString(4, shelter.getsPhone());
			pstmt.setString(5, shelter.getsEmail());
			pstmt.setString(6, shelter.getsId());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("회원정보 변경완료");
			} else {
				System.out.println("회원정보 변경실패");
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
	
	// 후원자 정보를 저장하기 위한 메소드
	public void deposit(ShelterDTO shelter) {
		String sql = "UPDATE SHELTER SET SPONSOR = SPONSOR + ? WHERE SID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shelter.getSponsor());
			pstmt.setString(2, shelter.getsId());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("입금 완료");
			} else {
				System.out.println("입금 실패");
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
	
	// 후원자 명단 조회를 위한 메소드
	public void sponList(ShelterDTO shelter) {
		String sql = "SELECT SID, SNAME, SPONSOR FROM SHELTER WHERE SPONSOR > 0";
		try {
			pstmt = conn.prepareStatement(sql);
		    rs = pstmt.executeQuery();
		    int i = 1;
		    
		    while(rs.next()) {
		    	System.out.println(i + "번째 후원자");
		    	System.out.println("후원자 ID : " + rs.getString(1));
		    	System.out.println("후원자 이름 : " + rs.getString(2));
		    	System.out.println("후원 금액 : " + rs.getInt(3));
		    	System.out.println("=====================================");
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
	
	// 후원금 총합
	public void sponSum(ShelterDTO shelter) {
		String sql = "SELECT SUM(SPONSOR) FROM SHELTER";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("후원금의 총액은 : " + rs.getInt(1) +"원 입니다.");
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