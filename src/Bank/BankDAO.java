package Bank;

import java.sql.*;

public class BankDAO {
	
	// DB접속을 위한 변수 선언
	Connection conn = null;
	
	// 쿼리문 전송을 위한 변수 선언
	PreparedStatement pstmt = null;
	
	// 조회결과를 저장하기 위한 변수 선언
	ResultSet rs = null;
	
	// DB접속을 위한 메소드connect()
	public void connect() {
		conn = DBC.DBConnect();
	}
	
	// DB접속 해제를 위한 메소드conClose()
	public void conClose() {
		try {
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	
	// 고객번호를 생성하기 위한 메소드clientNumber()
	public int clientNumber() {
		String sql = "SELECT COUNT(*) FROM BANK";	// DB에서 조회하면 '0' 조회됨
		int cNumber = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cNumber = rs.getInt(1);		// SELECT COUNT(*) FROM BANK
			}								// 로 조회된 값 '0'을 cNumber에 넣겠다.
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return cNumber;
	}

	
	// 1. 고객정보를 저장하기 위한 메소드insertClient()
	public void insertClient(BankDTO client) {
		String sql = "INSERT INTO BANK VALUES(?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, client.getClientNumber());
			pstmt.setString(2, client.getcName());
			pstmt.setString(3, client.getAccountNumber());			
			pstmt.setInt(4, client.getBalance());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("고객등록 성공!");
			} else {
				System.out.println("고객등록 실패!");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}

	// 2. 입금 메소드deposit()
	public void deposit(BankDTO client) {
		
		String sql = "UPDATE BANK SET BALANCE = BALANCE + ? "
				+ "WHERE ACCOUNTNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, client.getBalance());
			pstmt.setString(2, client.getAccountNumber());
			
			int result = pstmt.executeUpdate();
					  // ↓ 실제 데이터베이스 실행하는 영역
					  // (입금 성공여부 확인 필요없으면 "int result = "와 if문 없어도 됨)			
			if(result > 0) {
				System.out.println("입금 성공!");
			} else {
				System.out.println("입금 실패!");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	
	// 3. 출금 메소드withdraw()
	public void withdraw(String accountNumber, int balance) {
		
		String sql = "UPDATE BANK SET BALANCE = BALANCE - ? "
				+ "WHERE ACCOUNTNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, accountNumber);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("출금 성공!");
			} else {
				System.out.println("출금 실패!");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 4. 입력된 계좌번호의 잔액조회 메소드checkBalance()
	public int checkBalance(String accountNumber) {		// 메소드 자체가 int값
		String sql = "SELECT BALANCE FROM BANK WHERE ACCOUNTNUMBER = ?";
		int balance = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			
			rs = pstmt.executeQuery();	// select한 값을 rs에 담도록 하기위해
			
			if(rs.next()) {
				balance = rs.getInt(1);				// 첫번째 컬럼을 가져오겠다.
				// balance = rs.getInt("BALANCE");	// 컬럼BALANCE를 가져오겠다.
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
				
		return balance;
	}

	
	// 5. 계좌가 존재하는지 조회하는 메소드checkAccount()
	public boolean checkAccount(String accountNumber) {	// 이름은 달라도 된다.
		String sql = "SELECT ACCOUNTNUMBER FROM BANK "
					+ "WHERE ACCOUNTNUMBER = ?";
		boolean cAccount = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			
			rs = pstmt.executeQuery();	// DB에서 sql문을 실행했을 때 나오는 내용 전체가
										// rs에 저장된다.
			
			// rs의 결과값이 한개 이기 때문에 while아닌 if를 사용
			if(rs.next()) {	// if(rs.next()) => 해석 : 출력(조회)값이 있으면{}
							// while(rs.next()) => 첫번째행부터 마지막 행까지 반복후 
							//					   false값으로 변함(영어긴거있는데...)	
				cAccount = true;	// accountNumber 값(계좌번호)이 조회되면 true
									// 없으면 초기값으로 선언한 false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return cAccount;
	}
	
	
	// 5-2. 송금 메소드send()
	// 보내는사람 계좌번호, 받는사람 계좌번호, 송금액의 정보를 매개변수로 담아서 넘어옴
	public void send(String sAccountNumber, String rAccountNumber, int balance) {
		
		withdraw1(sAccountNumber, balance); // 보내는 사람 계좌에서 출금
		deposit1(rAccountNumber, balance);  // 받는 사람 계좌에 입금
		System.out.println("송금 성공!");		
	}	
	
	// 5-2-1). 보내는사람 계좌에서 송금액 빼기
	public void withdraw1(String accountNumber, int balance) {
		
		String sql = "UPDATE BANK SET BALANCE = BALANCE - ? "
				+ "WHERE ACCOUNTNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, accountNumber);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}	
	
	// 5-2-2). 받는사람 계좌에서 송금액 더하기
	public void deposit1(String accountNumber, int balance) {
		
		String sql = "UPDATE BANK SET BALANCE = BALANCE + ? "
				+ "WHERE ACCOUNTNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, accountNumber);
			
			pstmt.executeUpdate();			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
