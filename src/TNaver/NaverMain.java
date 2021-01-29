package TNaver;

import java.util.Scanner;

public class NaverMain {
	// 프론트엔드 , 나중에 웹페이지로 대체
		public static void main(String[] args) {
			
			// 서버를 개발하는 NaverDAO클래스
			NaverDAO server = new NaverDAO();	// 메소드
			
			// 회원가입 정보를 담고 있는 NaverDTO클래스
			NaverDTO naver = new NaverDTO();	// 필드
			
			Scanner sc = new Scanner(System.in);
			
			// 프로그램 실행을 위한 변수 run
			boolean run = true;
			
			// 항목을 선택할 때 필요한 변수 menu
			int menu = 0;
			
			// while문을 이용하여 프로그램 실행
			while(run) {
				System.out.println("======================");
				System.out.println("1.DB접속        2.DB접속해지");
				System.out.println("3.회원가입      4.회원조회");
				System.out.println("5.회원수정      6.회원삭제");
				System.out.println("7.프로그램종료");
				System.out.println("======================");
				System.out.print("항목 선택 >> ");
				menu = sc.nextInt();
				
				// 입력받은 항목(menu)을 실행하기 위한 switch문
				switch(menu) {
				case 1:
					server.connect();
					break;
					
				case 2:
					server.conClose();
					break;
					
				case 3:
					System.out.println("회원정보를 입력해주세요!");
					
					System.out.print("아이디 >> ");
					String nid = sc.next();
					naver.setnId(nid);
					
					System.out.print("비밀번호 >> ");
					String npw = sc.next();
					
					System.out.print("비밀번호 확인 >> ");
					String npwc = sc.next();
					
					if(npw.equals(npwc)) {
						System.out.println("사용가능한 비밀번호");
						naver.setnPw(npw);
					} else {
						System.out.println("비밀번호가 틀렸습니다.");
						break;
					}
					
					System.out.print("이름 >> ");
					String nname = sc.next();
					naver.setnName(nname);
					
					System.out.println("생년월일");
					System.out.print("연도 >> ");
					String nyear = sc.next();
					
					System.out.print("월 >> ");
					String nmon = sc.next();
					
					System.out.print("일 >> ");
					String nday = sc.next();
					
					System.out.print("생년월일 확인 : ");
					System.out.println(nyear+nmon+nday);
					naver.setnBirth(nyear+nmon+nday);
					
					System.out.print("성별 >> ");
					String ngen = sc.next();
					naver.setnGender(ngen);
					
					System.out.print("이메일 >> ");
					String nemail = sc.next();
					naver.setnEmail(nemail);
					
					System.out.print("휴대전화 >> ");
					String nphone = sc.next();
					naver.setnPhone(nphone);
					
					server.memberJoin(naver);
					// server(NaverDAO)의 memberJoin()메소드에
					// naver(NaverDTO)의 정보를 담아서 이동하겠다.
					
					break;
					
				case 4:
					// 아이디랑 비밀번호를 입력받아서
					// admin 아이디만 조회를 가능하게끔
					
					System.out.print("관리자 ID >> ");
					String adminId = sc.next();
					
					System.out.print("관리자 PW >> ");
					String adminPw = sc.next();
					
					if(adminId.equals("admin") & adminPw.equals("admin")) {
						server.memberList();
						
					} else {
						System.out.println("관리자가 아닙니다.");
					}					
					break;
					
				case 5:
					
					System.out.print("수정할 회원 아이디 >> ");
					nid = sc.next();
					naver.setnId(nid);
					
					System.out.print("비밀번호 >> ");
					npw = sc.next();
					
					System.out.print("비밀번호 확인 >> ");
					npwc = sc.next();
					
					if(npw.equals(npwc)) {
						System.out.println("사용가능한 비밀번호");
						naver.setnPw(npw);
					} else {
						System.out.println("비밀번호가 틀렸습니다.");
						break;
					}
					
					System.out.print("이름 >> ");
					nname = sc.next();
					naver.setnName(nname);
					
					System.out.println("생년월일");
					System.out.print("연도 >> ");
					nyear = sc.next();
					
					System.out.print("월 >> ");
					nmon = sc.next();
					
					System.out.print("일 >> ");
					nday = sc.next();
					
					System.out.print("생년월일 확인 : ");
					System.out.println(nyear+nmon+nday);
					naver.setnBirth(nyear+nmon+nday);
					
					System.out.print("성별 >> ");
					ngen = sc.next();
					naver.setnGender(ngen);
					
					System.out.print("이메일 >> ");
					nemail = sc.next();
					naver.setnEmail(nemail);
					
					System.out.print("휴대전화 >> ");
					nphone = sc.next();
					naver.setnPhone(nphone);
					
					server.memberModify(naver);
					break;
				case 6:
					System.out.println("삭제할 회원 아이디 조회!");
					
					System.out.print("삭제할 아이디 >> ");
					String dId = sc.next();
					
					System.out.print("비밀번호 >> ");
					String dPw = sc.next();
					
					boolean check = server.idCheck(dId,dPw);
					// boolean타입의 변수 check선언
					// server(NaverDAO)에서 dId와 dPw의 정보를 담은
					// boolean타입의 메소드 idCheck생성
					
					if(check) {
						server.memberDelete(dId);
					} else {
						System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
					}
					
					break;
					
				case 7:
					run = false;
					// run이 true이기 때문에 반복문이 실행
					// run을 false로 바꾸면 반복문이 종료된다.
					System.out.println("프로그램을 종료합니다!");
					break;
				default:
					System.out.println("다시 입력하세요!");
					break;
				
				} //end switch
				
			} //end while
			
			

		} //end main

} // end class
	

