package Jdbc0120;

import java.util.Scanner;

public class Stu_Main {

	public static void main(String[] args) {
		// SQL문을 실행하기 위한 객체 선언
		Stu_SQL sql = new Stu_SQL();
		
		// stu 객체선언
		StuDTO stu = new StuDTO();
		
		// 정보를 입력받기 위한 sc객체 선언
		Scanner sc = new Scanner(System.in);
		
		boolean run = true;
		int menu = 0;
		
		while(run) {
			System.out.println("======================");
			System.out.println("1. DB 접속        2. DB 해제");
			System.out.println("3. 학생등록        4. 학생조회");
			System.out.println("5. 학생수정        6. 학생삭제");
			System.out.println("7. 종      료");
			System.out.println("======================");
			System.out.print("항목 선택 >> ");
			menu = sc.nextInt();
			
			// switch문 작성
			switch(menu) {			
			case 1:
				sql.connect();
				break;
			case 2:
				sql.conClose();
				break;
			case 3:
				System.out.println("학생정보를 입력해주세요.");
				System.out.print("이름 >> ");
				String stuName = sc.next();
				
				System.out.print("나이 >> ");
				int stuAge = sc.nextInt();
				
				stu.setStuName(stuName);
				stu.setStuAge(stuAge);
				// stu.StuAge = stuAge 라고 못쓴다. private이라
				
				sql.insert(stu);
				
				break;
			case 4:
				sql.select();
				break;
			case 5:
				System.out.println("수정할 학생정보를 입력해주세요.");
				
				System.out.print("변경할 학생이름 >> ");		// PK
				stuName = sc.next();
				
				System.out.print("변경할 나이 >> ");
				stuAge = sc.nextInt();
				
				stu.setStuName(stuName);
				stu.setStuAge(stuAge);
				
				sql.update(stu);
				
				break;
			case 6:
				System.out.println("삭제할 학생정보를 입력해주세요.");
				
				System.out.print("삭제할 학생이름 >> ");
				String dName = sc.next();
				
				sql.delete(dName);
				break;
			case 7:
				run = false;
				break;
			default:
				System.out.println("잘못 입력!");
				break;
			}	// end switch
		}	// end while
		System.out.println("시스템 종료!");
	}

}
