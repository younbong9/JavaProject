package Bank;

import java.util.Scanner;

public class BankMain {

	public static void main(String[] args) {

		// BankDTO의 정보를 담는 객체client 선언
		BankDTO client = new BankDTO();				// BankDTO : 고객정보 client
		
		// BankDAO의 정보를 담는 객체 server 선언
		BankDAO server = new BankDAO();				// BankDAO : 메소드 server
		
		// 입력을 위한 sc객체 선언
		Scanner sc = new Scanner(System.in);
		
		// 프로그램 실행을 위한 변수 run 선언
		boolean run = true;
		
		// 항목을 선택할 때 필요한 변수 menu 선언
		int menu = 0;
		
		// while문을 사용하여 프로그램 실행
		System.out.println("ICIA은행에 오신 것을 환영합니다.");
		System.out.println("원하시는 메뉴를 선택해주세요!");
		System.out.println();
		
		while(run) {
			System.out.println("======================");
			System.out.println("1.계좌생성            2.입      금");
			System.out.println("3.출      금            4.잔액조회");
			System.out.println("5.송      금            6.종      료");
			System.out.println("======================");
			System.out.print("메뉴 선택 >> ");
			menu = sc.nextInt();
			server.connect();
			
			switch(menu) {			
			case 1:
				int clientNumber = server.clientNumber()+1 ;
				
				System.out.println("회원정보를 입력해주세요!");
				System.out.print("회원이름 >> ");
				String cName = sc.next();
				
				System.out.print("계좌번호 >> ");
				String accountNumber = sc.next();
				
				System.out.print("초기 입금액 >> ");
				int balance = sc.nextInt();
				// client.setBalance(balance);	// 예전방식(getter setter)
				
				// 파라미터를 이용한 생성자 만듬
				client = new BankDTO(clientNumber, cName, accountNumber, balance);
				
				server.insertClient(client);
				
				break;
				
			case 2:
				System.out.print("계좌번호 >> ");
				accountNumber = sc.next();
				
				System.out.print("입금액 >> ");
				balance = sc.nextInt();
				
				// 위에서 입력한 정보(계좌번호,입금액)를 객체client에 저장하는 방법
				//  client = new BankDTO(clientNumber, cName, accountNumber, 
				//														balance);
				// ↑위의 문장을 쓰거나 ↓아래 2문장을 쓰거나 (둘 다 같은 정보저장방법)
				// ↑위의 문장을 쓰려면 clientNumber=0;, cName = null;로 선언한 뒤 사용
				
				client.setAccountNumber(accountNumber);
				client.setBalance(balance);
				
				// (1) 방법 : BankDTO(client)의 정보를 모두 넘긴다.
				server.deposit(client);
				
				// (2) 방법 : accountNumber(계좌번호)와 balance(임금액) 정보만 넘긴다.
				// server.deposit(accountNumber, balance);
				
				break;
				
			case 3:
				// 잔액조회를 사용해서 출금액이 잔액보다 많을경우 출금되지 못하도록 코드 짜기
				System.out.print("계좌번호 >> ");
				accountNumber = sc.next();
				
				System.out.print("출금액 >> ");
				balance = sc.nextInt();
				
				// System.out.println(client.getBalance());
				// 잔액이 얼마인지 볼라고 한건데, 이렇게 하면 '0'이 출력된다.
				// 왜냐하면 어떤 행 또는 정보(조건식)를 주고 balance값을 찾아야 하는데 그게 없으니까~
				
				
//				if(client.getBalance() < balance) {			// 내코드
//					System.out.println("잔액이 부족합니다."); 
//					System.out.println("현재 잔액은 " + 
//							server.checkBalance(accountNumber) + "원 입니다.");
//					break;
//				}

				int cBalance = server.checkBalance(accountNumber);	// 선생님코드
				// cBalance는 현재 출금하고자 하는 계좌의 잔액
				
				if(cBalance < balance) {			
					System.out.println("출금액이 " + (balance - cBalance) +
							"원 부족합니다."); 
					System.out.println("현재 잔액은 " + cBalance + 
							"원, 출금 요청금액은 " + balance + "원 입니다.");
					break;
				}
						
				// (1) 방법 : BankDTO(client)의 정보를 모두 넘긴다.
				// client = new BankDTO(clientNumber,cName,accountNumber,balance);
					// ↓→ 입력한 정보 client에 저장하기
				// server.withdraw(client);
				
				
				
				// client.setAccountNumber(accountNumber);
				// client.setBalance(balance);
				
				// (2) 방법 : accountNumber(계좌번호)와 balance(임금액) 정보만 넘긴다.
				server.withdraw(accountNumber, balance);				
				break;				
				
			case 4:
				System.out.print("계좌번호 >> ");
				accountNumber = sc.next();
				
				balance = server.checkBalance(accountNumber);
				// balance가 int타입/
				// server.checkBalance(accountNumber); 로 메소드 만들면 void로 생성되기때문
				
				System.out.println("잔액조회 : " + balance + "원 입니다.");
				
				break;
				
			case 5:			
			 // 1. 송금받을 계좌가 있는지?
			 // 2. 송금할 계좌의 잔액이 송금액보다 많지 않은지?
				
  			 // 1. 송금받을 계좌가 있는지?
				// 보내는사람 계좌 : sAccountNumber
				System.out.print("송금 할 계좌번호 >> ");
				String sAccountNumber = sc.next();
				
				// 받는사람 계좌 : rAccountNumber
				System.out.print("송금 받을 계좌번호 >> ");
				String rAccountNumber = sc.next();
				
				System.out.print("송금액 >> ");
				balance = sc.nextInt();
				
				// 보내는 계좌, 받는 계좌가 조회되는지
				// true면 계좌가 존재함, false면 계좌가 존재하지않음;
				boolean sAccount = server.checkAccount(sAccountNumber);
				boolean rAccount = server.checkAccount(rAccountNumber);
				
				
			 // 2. 송금할 계좌의 잔액이 송금액보다 많지 않은지?
				// sBalance : 보내는 사람 계좌잔액
				int sBalance = server.checkBalance(sAccountNumber);					
				
				
				// 1. 보내는 사람 계좌번호
				if(sAccount) {		// if문은 ()안의 내용이 true이면 {}내용 실행
									// 		()안의 내용이 false이면 else{}내용 실행
					// 2. 받는 사람 계좌번호
					if(rAccount) {
						
						// 3. 잔액이 송금보다 많아야 한다.
						if(sBalance >= balance) {
							
							// server에 send() 메소드 생성
							server.send(sAccountNumber, rAccountNumber, balance);
						} else {
							System.out.println("송금액이 " + (balance - sBalance) +
									"원 부족합니다."); 
							System.out.println("현재 잔액은 " + sBalance + 
									"원, 송금 요청금액은 " + balance + "원 입니다.");
						}
					}else {
						System.out.println("받으실 분의 계좌번호를 확인해주세요!");
					}
				} else {
					System.out.println("보내실 분의 계좌번호를 확인해주세요!");
				}		
				break;
				
			case 6:
				run = false;
				System.out.println("이용해주셔서 감사합니다!");
				break;
				
			default :
				System.out.println("다시 입력해주세요!");
				break;						
			}	// end switch			
			server.conClose();
		}	// end while
		
	}

}
