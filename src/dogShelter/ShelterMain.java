package dogShelter;

import java.util.Scanner;

public class ShelterMain {

	public static void main(String[] args) {
 
		ShelterDTO shelter = new ShelterDTO();
		ShelterDTO2 dog = new ShelterDTO2();
		ShelterDTO3 donate = new ShelterDTO3();
		ShelterDAO server = new ShelterDAO();
		Scanner scan = new Scanner(System.in);
		boolean op = true;

		while (op) {
			System.out.println();
			System.out.println("유기견 조회 입양 프로그램 [ 데려가개 ] 입니다!");
			System.out.println("             ⎛⎝(•‿•)⎠⎞⎛⎝(•‿•)⎠⎞⎛⎝(•‿•)⎠⎞⎛⎝(•‿•)⎠⎞              ");
			System.out.println();
			System.out.println("________________________________");
			System.out.println("1.로그인 2.회원가입 3.관리자 로그인 4.종료");
			System.out.print("______________Select Number >> ");
			
			int menu = scan.nextInt();
			server.connect(); // DB connect

			switch (menu) {
			case 1: // 1.로그인
				System.out.println();
				System.out.print("아이디 >> ");
				String sId = scan.next();
				System.out.print("비밀번호 >> ");
				String sPw = scan.next();
				boolean check = server.logIn(sId, sPw);

				if (check) { // check == true
					boolean op1 = true;
					while (op1) {
						System.out.println();
						System.out.println("_____________ Menu ______________");
						System.out.println("1.유기견 조회  2.후원하기 3.입양 4.로그아웃");
						System.out.print("_______________Select Number >> ");
						int subMenu = scan.nextInt();

						switch (subMenu) {
						case 1: // 1-1.유기견조회
							boolean op2 = true;
							while (op2) {
								System.out.println();
								System.out.println("____________ Search Dog ____________");
								System.out.println("1.전체조회  2.품종별 조회 3.성별 조회 4.뒤로가기");
								System.out.print("__________________Select Number >> ");
								int search = scan.nextInt();

								switch (search) {
								case 1: // 1-2-1.전체조회
									System.out.println();
									System.out.println("[ 전체 조회를 선택하셨습니다. ]");
									server.dogList();
									break;

								case 2: // 1-2-2.품종별조회
									System.out.println();
									System.out.println("[ 품종별 조회를 선택하셨습니다. ]");
									System.out.println();
									System.out.print("조회할 품종 >> ");
									String searchBreed = scan.next();
									server.dogListB(searchBreed);
									break;

								case 3: // 1-2-3.성별조회
									System.out.println();
									System.out.println("[ 성별 조회를 선택하셨습니다. ]");
									System.out.println();
									System.out.print("조회할 성별(여아,남아) >> ");
									String searchGender = scan.next();
									server.dogListG(searchGender);
									break;

								case 4: // 1-2-4.뒤로가기
									op2 = false;
									System.out.println("[ 뒤로가기 메뉴를 누르셨습니다. ]");
									break;

								default:
									System.out.println("[ 메뉴를 잘못 누르셨습니다. ]");
									break;
								} // end switch(search)
							} // end while(op2)
							break;

						case 2: // 1-2.후원
							System.out.println();
							System.out.println("[ 유기견 후원 메뉴입니다. ]");
							System.out.print("아이디 >> ");
							String dId = scan.next();
																											
							System.out.print("후원 금액 >> ");
							int money = scan.nextInt();
							
							server.sumDon(dId, money);
							server.spon(dId, money);
							System.out.println();
							System.out.println("[ 후원하신 금액은 " + money + "원 입니다. ]");
							break;

						case 3: // 1-3. 입양
							System.out.println();
							System.out.println("[ 유기견 입양 메뉴입니다. ]");
							System.out.println("[ 입양하실 유기견의 정보를 입력해주세요. ]");
							System.out.print("공고번호 >> ");
							String sNum = scan.next();

							System.out.print("품종 >> ");
							String sBreed = scan.next();

							System.out.print("성별 >> ");
							String sGender = scan.next();

							server.dogAdop(sNum, sBreed, sGender);
							break;

						case 4: // 1-4.로그아웃
							System.out.println();
							System.out.println("[ 로그아웃! ]");
							op1 = false;
							break;

						default:
							System.out.println();
							System.out.println("[ 메뉴를 다시 선택해주세요! ]");
							break;
						} // end switch(menu3)
					} // end while(search)

				} else { // end if(check)
					System.out.println();
					System.out.println("[ 아이디와 비밀번호를 다시 확인바랍니다. ]");
				}
				break;
				
/////////////////////////////////////////////////////////////////////////////////////////////////

			case 2: // 2.회원가입
				System.out.println();
				System.out.println("[ 가입할 정보 입력 ]");
				System.out.print("아이디 >> ");
				sId = scan.next();

				System.out.print("비밀번호 >> ");
				sPw = scan.next();

				System.out.print("비밀번호 확인 >> ");
				String sPwc = scan.next();
				if (sPw.equals(sPwc)) {
					System.out.println("[ 비밀번호가 일치합니다! ]");
				} else {
					System.out.println("[ 비밀번호가 일치하지 않습니다! ]");
				}

				System.out.print("이름 >> ");
				String sName = scan.next();

				System.out.print("주소>> ");
				scan.nextLine();
				String sAddr = scan.nextLine();
				
				System.out.print("연락처 >> ");
				String sPhone = scan.next();

				System.out.print("이메일 >> ");
				String sEmail = scan.next();

				server.memberJoin(sId, sPw, sName, sAddr, sPhone, sEmail);
				break;

/////////////////////////////////////////////////////////////////////////////////////////////////		

			case 3: // 3.관리자 로그인
				System.out.println();
				System.out.println("[ 관리자 메뉴입니다. ]");
				System.out.println();
				System.out.print("관리자아이디 >> "); // id = admin
				String adminId = scan.next();

				System.out.print("비밀번호 >> "); // pw = admin
				String adminPw = scan.next();

				if (adminId.equals("admin") & adminPw.equals("admin")) {
					boolean admin = true;
					while (admin) {
						System.out.println();
						System.out.println("________________ Management ________________");
						System.out.println("1.유기견 관리    2. 회원관리    3. 후원금관리   4. 로그아웃");
						System.out.print("__________________________Select Number >> ");
						int aMenu = scan.nextInt();

						switch (aMenu) {
						case 1: // 3-1.유기견관리
							boolean manage = true;
							while (manage) {
								System.out.println();
								System.out.println("[ 유기견 관리 메뉴입니다. ]");
								System.out.println();
								System.out.println("________________ Dog Management ________________");
								System.out.println("1.유기견 정보등록 2.유기견 정보수정 3.유기견 정보삭제 4.뒤로가기");
								System.out.print("______________________________Select Number >> ");
								int tMenu = scan.nextInt();

								switch (tMenu) {
								case 1:	// 3-1-1.유기견 정보등록
									boolean regist = true;
									while(regist) {	
										System.out.println();
										System.out.println("[ 유기견 등록 메뉴입니다. ]");
										System.out.println();
										System.out.println("___ Registration ___");
										System.out.println("1.등록              2.뒤로가기");
										System.out.print("____Select Number >> ");
										int rMenu = scan.nextInt();
										
										switch (rMenu) {
										case 1:
											System.out.println();
											System.out.println("[ 유기견 등록 메뉴입니다. ]");
											System.out.print("공고번호 >> ");
											String sNum = scan.next();
											dog.setsNum(sNum);

											System.out.print("접수일자 >> ");
											String sDate = scan.next();
											dog.setsDate(sDate);

											System.out.print("품종 >> ");
											String sBreed = scan.next();
											dog.setsBreed(sBreed);
									
											System.out.print("성별 >> ");
											String sGender = scan.next();
											dog.setsGender(sGender);

											System.out.print("특징 >> ");
											scan.nextLine();
											String sChar = scan.nextLine();
//											scan.nextLine();											
											dog.setsChar(sChar);

											System.out.print("추정나이 >> ");
											String sEstiAge = scan.next();
											dog.setsEstiAge(sEstiAge);

											server.dogReg(sNum, sDate, sBreed, sGender, sChar, sEstiAge);
											break;
											
										case 2:
											regist = false;
											break;
											
										default :
											System.out.println("[ 잘못 누르셨습니다! ]");
											break;
											
										}	// end switch(rMenu)
										
										
									}	// end while(regist)
									break;
									
								case 2:	// 3-1-2.유기견 정보수정
									boolean modify = true;
									while(modify) {
										System.out.println();
										System.out.println("[ 유기견 정보수정 메뉴입니다. ]");
										System.out.println();
										System.out.println("_____ Modification _____");
										System.out.println("1.정보수정               2.뒤로가기");
										System.out.print("______Select Number >> ");
										int mMenu = scan.nextInt();
										
										switch(mMenu) {
										case 1:
											System.out.println();
											System.out.println("[ 유기견 정보수정 메뉴입니다. ]");
											System.out.print("유기견 공고번호 >> ");
											String sNum = scan.next();
											dog.setsNum(sNum);
											
											System.out.println();
											System.out.println("[ 변경할 정보 ]");
											System.out.print("접수일자 >> ");
											String sDate = scan.next();
											dog.setsDate(sDate);

											System.out.print("품종 >> ");
											String sBreed = scan.next();
											dog.setsBreed(sBreed);

											System.out.print("성별 >> ");
											String sGender = scan.next();
											dog.setsGender(sGender);

											System.out.print("특징 >> ");
											scan.nextLine();
											String sChar = scan.next();
//											scan.nextLine();
											dog.setsChar(sChar);

											System.out.print("추정나이 >> ");
											String sEstiAge = scan.next();
											dog.setsEstiAge(sEstiAge);

											server.dogModify(sNum, sDate, sBreed, sGender, sChar, sEstiAge);
											break;
											
										case 2:
											modify = false;
											break;
											
										default :
											System.out.println("[ 잘못 누르셨습니다! ]");
											break;
											
										}	// end switch(mMenu)										
									}	// end while(modify)
									break;
									
								case 3:	// 3-1-3.유기견 정보삭제
									boolean delete = true;
									while(delete) {
										System.out.println();
										System.out.println("[ 유기견 정보삭제 메뉴입니다. ]");
										System.out.println();
										System.out.println("_____ Elimination _____");
										System.out.println("1.정보삭제             2.뒤로가기");
										System.out.print("_____Select Number >> ");
										int dMenu = scan.nextInt();
										
										switch(dMenu) {
										case 1:
											System.out.println();
											System.out.println("[ 유기견 정보삭제 메뉴입니다. ]");
											System.out.print("공고번호 >> ");
											String sNum = scan.next();
											dog.setsNum(sNum);

											server.dogDel(sNum);
											break;
											
										case 2:
											delete = false;											
											break;
											
										default :
											System.out.println("[ 잘못 누르셨습니다! ]");
											break;
											
										}	// end switch(dMenu)
									}	// end while(delete)
									break;
									
								case 4:	// 3-1-4. 뒤로가기
									manage = false;
									break;
									
								default :
									System.out.println("[ 잘못 누르셨습니다! ]");
									break;
									
								} // end switch(tMenu)

							} // end while(manage)
							break;
							
							
							

						case 2: // 3-2.회원관리
							boolean memManage = true;
							while(memManage) {
								System.out.println();
								System.out.println("[ 회원관리 메뉴입니다. ]");
								System.out.println();
								System.out.println("_____________ Member Management _____________");
								System.out.println("1. 회원조회   2. 회원정보 수정   3. 회원 삭제   4. 뒤로가기");
								System.out.print("___________________________Select Number >> ");
								int member = scan.nextInt();
								
								switch(member) {
								case 1:	// 3-2-1. 회원조회
									System.out.println();
									System.out.println("[ 회원조회 메뉴입니다. ]");
									System.out.println();
									System.out.print("조회할 아이디 >> ");
									String searchId = scan.next();
									server.searchMember(searchId);
									break;
									
								case 2:	// 3-2-2. 회원정보수정
									System.out.println();
									System.out.println("[ 회원정보 수정메뉴입니다. ]");
									System.out.println();
									System.out.print("정보를 수정할 회원 아이디 >> ");
									String mId = scan.next();
									boolean memCheck = server.memCheck(mId);
									if(memCheck) {
										shelter.setsId(mId);
										
										System.out.print("비밀번호 >> ");
										String mPw = scan.next();
										shelter.setsPw(mPw);
										
										System.out.print("이름 >> ");
										String mName = scan.next();
										shelter.setsName(mName);
										
										System.out.print("주소 >> ");
										scan.nextLine();
										String mAddr = scan.nextLine();										
										
										shelter.setsAddr(mAddr);
										
										System.out.print("연락처 >> ");
										String mPhone = scan.next();
										shelter.setsPhone(mPhone);
										
										System.out.print("이메일 >> ");
										String mEmail = scan.next();
										shelter.setsEmail(mEmail);
										
										server.updateMember(shelter);
										
									} else {
										System.out.println("[ 존재하지 않는 아이디입니다. ]");
									}
									
									break;
									
								case 3:	// 3-2-3. 회원정보삭제
									System.out.println();
									System.out.println("[ 회원정보 삭제메뉴입니다. ]");
									System.out.println();
									System.out.print("정보를 삭제할 회원 아이디 >> ");
									String dId = scan.next();
									boolean memCheck2 = server.memCheck2(dId);
									if(memCheck2) {										
										server.deleteMember(dId);
									} else {
										System.out.println("[ 존재하지 않는 아이디입니다. ]");
									}
									
									break;
									
								case 4:	// // 3-2-4. 뒤로가기
									memManage = false;
									break;
								
								default:
									System.out.println("[ 잘못 누르셨습니다! ]");
									break;
								}	// end switch(member)
								
							}	// end while(memManage)
							break;

						case 3: // 3-3.후원금관리
							boolean donation = true;
							while(donation) {
								System.out.println();
								System.out.println("________ Donation ________");
								System.out.println("1.후원금 조회                2.뒤로가기");
								System.out.print("_________Select Number >> ");
								int dMenu = scan.nextInt();
								switch(dMenu) {
								case 1:
									System.out.println();
									System.out.print("조회할 아이디 >> ");
									String donId = scan.next();
									
									server.don(donId);
									break;
									
								case 2:
									donation = false;
									break;
									
								default:
									System.out.println("[ 잘못 누르셨습니다! ]");
									break;
								}	// end switch(dMenu)
								
							}	// end while(donation)
							break;

						case 4: // 3-4.로그아웃
							System.out.println("[ 로그아웃! ]");
							admin = false;
							break;

						default:
							System.out.println("[ 잘못 누르셨습니다. ]");
							break;

						} // end switch(aMenu)
					} // end while(admin)

				} else { // end if
					System.out.println("[ 관리자가 아닙니다. 관리자 재로그인 바랍니다. ]");
				} // end else

/////////////////////////////////////////////////////////////////////////////////////////////////
			case 4: // 4.종료
				op = false;
				System.out.println("[ 이용해 주셔서 감사합니다. ]");
				break;

			default:
				System.out.println("[ 다시 메뉴를 선택해주세요. ]");
				break;

			} // end switch(menu)
			server.conClose(); // DB disconnect
		} // end while(op)

	}
}