package Naver;

import java.util.Scanner;

public class NaverMain {

	public static void main(String[] args) {
		
		// SQL문 실행위한 객체dao선언
		NaverDAO dao = new NaverDAO();		// NaverDAO : DB연결과 실제활동 메소드
		
		// 객체dto 선언
		NaverDTO dto = new NaverDTO();	// NaverDTO : 필드들
		
		Scanner sc = new Scanner(System.in);

		boolean run = true;
		int menu = 0;
		
		while(run) {
			System.out.println("======================");
			System.out.println("1. DB 접속        2. DB 해제");
			System.out.println("3. 회원가입        4. 가입목록");
			System.out.println("5. 회원수정        6. 회원탈퇴");
			System.out.println("7. 종      료");
			System.out.println("======================");
			System.out.print("항목 선택 >> ");
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				dao.connect();
				break;
			case 2:
				dao.conClose();
				break;
			case 3:
				System.out.println("회원가입 정보를 입력해주세요.");
				System.out.print("아이디 >> ");
				String naverId = sc.next();
				
				System.out.print("비밀번호 >> ");
				String naverPw = sc.next();
				
				System.out.print("이름 >> ");
				String naverName = sc.next();
				
				System.out.print("생년월일 >> ");
				int naverBirth = sc.nextInt();
				
				System.out.print("성별 >> ");
				String naverXy = sc.next();
				
				System.out.print("본인 확인 이메일 >> ");
				String naverMail = sc.next();
				
				System.out.print("휴대전화번호 >> ");
				String naverPhone = sc.next();
				
				dto.setNaverId(naverId);
				dto.setNaverPw(naverPw);
				dto.setNaverName(naverName);
				dto.setNaverBirth(naverBirth);
				dto.setNaverXy(naverXy);
				dto.setNaverMail(naverMail);
				dto.setNaverPhone(naverPhone);
				
				dao.insert(dto);
				
				break;
			case 4:
				dao.select();
				break;
			case 5:
				System.out.println("수정할 회원정보를 입력해주세요.");
				
				System.out.print("회원 ID >> ");
				naverId = sc.next();
				
				System.out.print("변경할 비밀번호 >> ");
				naverPw = sc.next();
				
				System.out.print("변경할 이름 >> ");
				naverName = sc.next();
				
				System.out.print("변경할 생년월일 >> ");
				naverBirth = sc.nextInt();
				
				System.out.print("변경할 성별 >> ");
				naverXy = sc.next();
				
				System.out.print("변경할 이메일 >> ");
				naverMail = sc.next();
				
				System.out.print("변경할 휴대전화번호 >> ");
				naverPhone = sc.next();
				
				dto.setNaverId(naverId);
				dto.setNaverPw(naverPw);
				dto.setNaverName(naverName);
				dto.setNaverBirth(naverBirth);
				dto.setNaverXy(naverXy);
				dto.setNaverMail(naverMail);
				dto.setNaverPhone(naverPhone);
				
				dao.update(dto);
				
				break;
			case 6:
				System.out.println("삭제할 회원정보를 입력해주세요.");
				
				System.out.print("삭제할 회원ID >> ");
				String dId = sc.next();
				
				dao.delete(dId);
				break;
			case 7:
				run = false;
				break;
			default :
				System.out.println("잘못 입력!");
				break;
			} // end switch
		} // end while
		System.out.println("시스템 종료!");

	}

}
