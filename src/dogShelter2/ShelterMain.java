package dogShelter2;

import java.util.Scanner;

public class ShelterMain {

	public static void main(String[] args) {
		boolean op = true;
		ShelterDTO shelter = new ShelterDTO();
		DogDTO dog = new DogDTO();
		ShelterDAO server = new ShelterDAO();
		Scanner scan = new Scanner(System.in);
		
		while(op) {
			System.out.println("===============================");
			System.out.println("1.로그인  2.회원가입  3.유기견메뉴  4.종료");
			System.out.println("===============================");
			System.out.print("메뉴를 선택 하세오 >> ");
			int menu = scan.nextInt();
			server.connect(); // DB connect
			
			switch(menu) {
			case 1: System.out.print("아이디 >> ");		// 1.로그인
					String sId = scan.next();
					System.out.print("비밀번호 >> ");
					String sPw = scan.next();
					boolean check = server.idCheck(sId,sPw);
					if(check) {
						System.out.println("=========== Menu =============");
						System.out.println("1.유기견 조회  2.후원하기  3.로그아웃");
						System.out.println("===============================");
						System.out.print("메뉴를 선택 하세오 >> ");
						int subMenu = scan.nextInt();
						boolean op1 = true;
						
						while(op1) {
							switch(subMenu) {
							case 1: System.out.println("========= Search Dog ==========");
									System.out.println("1.전체조회  2.품종별 조회  3.뒤로가기");
									System.out.println("===============================");
									System.out.print("메뉴를 선택 하세오 >> ");
									int search = scan.nextInt();
									boolean op2 = true;
									
									while(op2) {
										switch(search) {
										case 1: server.dogList(); break;
										case 2: System.out.println("========== Breed ===========");
												System.out.println("1.품종1  2.품종2  3.뒤로가기");
												System.out.println("============================");
												System.out.print("원하는 품종을 선택 하세오 >> ");
												int dNum = scan.nextInt();
												String breed = null;
												if(dNum==1) {
													breed = "breed1";
													server.dogListByBreed(breed);
												}
												else if(dNum==2) {
													breed = "breed2";
													server.dogListByBreed(breed);
												}
												else {break;}
												
												break;
										case 3: System.out.println("goto previous menu"); op2 = false; break;
										default: System.out.println("Re-enter the menu."); break;
										}
									}
									break;
							case 2: System.out.print("How much do you donate? ");
									int money = scan.nextInt();
									server.donation(money); 
									break;
							case 3: System.out.println("Log out!"); op1=false; break;
							default: System.out.println("Re-enter the menu."); break;
							}
						}
					}else {
						System.out.println("You entered wrong ID or Password.");
					}
					break;
			case 2: System.out.println("회원정보 입력");		// 2.회원가입
					System.out.print("아이디 >> ");
					sId = scan.next();
					shelter.setsId(sId);
					System.out.print("비밀번호 >> ");
					sPw = scan.next();
					System.out.print("비밀번호 확인 >> ");
					String sPwc = scan.next();
					if(sPw.equals(sPwc)) {
						System.out.println("Correct PW");
						shelter.setsPw(sPw);
					}else {
						System.out.println("Incorrect PW");
					}
					System.out.print("이름 >> ");
					String sName = scan.next();
					shelter.setsName(sName);
					System.out.print("주소>> ");
					String sAddr = scan.next();
					shelter.setsAddr(sAddr);
					scan.nextLine();
					System.out.print("이메일 >> ");
					String sEmail = scan.next();
					shelter.setsEmail(sEmail);
					System.out.print("전화번호 >> ");
					String sPhone = scan.next();
					shelter.setsPhone(sPhone);
					
					server.memberJoin(shelter);
					break;
			case 3: System.out.print("관리자아이디 >> "); //id = admin		// 3.유기견메뉴
					String adminId = scan.next();
					System.out.print("비밀번호 >> ");
					String adminPw = scan.next();
					boolean op2 = true;
					if(adminId.equals("admin")) {
						while(op2) {
							System.out.println("================== Dog =================");
							System.out.println("1.유기견 등록 2.유기견 수정  3.유기견 삭제  4. 로그아웃");
							System.out.println("========================================");
							System.out.print("메뉴를 선택 하세오 >> ");
							int dsearch = scan.nextInt();
							
							switch(dsearch) {
							case 1: System.out.print("공고번호(ID) >> ");
									String dNo = scan.next();
									dog.setdNo(dNo);
									System.out.print("이름 >> ");
									String dName = scan.next();
									dog.setdName(dName);
									System.out.print("추정나이 >>");
									int dAge = scan.nextInt();
									dog.setdAge(dAge);
									System.out.print("품종 >> ");
									String dBreed = scan.next();
									dog.setdBreed(dBreed);
									System.out.print("성별 >> ");
									String dSex = scan.next();
									dog.setdSex(dSex);
									System.out.print("접수날짜 >> ");
									String dRegitDate = scan.next();
									dog.setdRegitDate(dRegitDate);
									
									server.addDog(dog);
									break;
							case 2: System.out.print("수정할 유기견 공고번호(ID) >> ");
									String modDNo = scan.next();
									boolean nCheck = server.dogCheck(modDNo);
									if(nCheck) {
										System.out.print("공고번호(ID) >> ");
										dNo = scan.next();
										dog.setdNo(dNo);
										System.out.print("이름 >> ");
										dName = scan.next();
										dog.setdName(dName);
										System.out.print("추정나이 >>");
										dAge = scan.nextInt();
										dog.setdAge(dAge);
										System.out.print("품종 >> ");
										dBreed = scan.next();
										dog.setdBreed(dBreed);
										System.out.print("성별 >> ");
										dSex = scan.next();
										dog.setdSex(dSex);
										System.out.print("접수날짜 >> ");
										dRegitDate = scan.next();
										dog.setdRegitDate(dRegitDate);
										
										server.modifyDog(dog);
									}else {
										System.out.println("There is no the dog no(id) entered.");
									}
									break;
									
							case 3: System.out.print("삭제할 유기견 공고번호(ID) >> ");
									String delDNo = scan.next();
									boolean dCheck = server.dogCheck(delDNo);
									if(dCheck) {
										server.deleteDog(delDNo);
									}else {
										System.out.println("There is no the dog no(id) entered.");
									}
									break;
									
							case 4: System.out.println("Log out!"); op2=false; break;
							default : System.out.println("Re-enter the menu."); break;
							}
						}
					}else {
						System.out.println("관리자가 아닙니다.");
					}
					
					break;
			case 4: op = false; System.out.println("Good bye~!"); break;
			default: System.out.println("Re-enter the menu."); break;
			}
			server.conClose(); // DB disconnect
		}
	}

}