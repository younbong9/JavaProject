package JH_dogShelter;

import java.util.Scanner;

public class ShelterMain {

	public static void main(String[] args) {
		boolean op = true;
		ShelterDTO shelter = new ShelterDTO();
		ShelterDAO server = new ShelterDAO();
		ShelterDTO2 dog = new ShelterDTO2();
//		ShelterDTO3 spon = new ShelterDTO3();
		Scanner sc = new Scanner(System.in);

		while (op) {
			System.out.println("===================================");
			System.out.println("1.로그인   2.회원가입   3.관리자로그인   4.종료");
			System.out.println("===================================");
			System.out.print("메뉴를 선택하시오 >> ");
			int menu = sc.nextInt();
			server.connect(); // DB connect

			switch (menu) {

			case 1: // 1.로그인
				System.out.print("아이디 >> ");
				String sId = sc.next();

				System.out.print("비밀번호 >> ");
				String sPw = sc.next();
				boolean check = server.idCheck(sId, sPw);

				if (check) { // check == true
					boolean run = true;
					while (run) {
						System.out.println("===================================");
						System.out.println(" 1.입양        2.조회       3.후원     4. 로그아웃");
						System.out.println("===================================");
						System.out.print("메뉴를 선택하시오 >> ");
						int menu2 = sc.nextInt();

						switch (menu2) {
						case 1: // 입양
							System.out.println("유기견 입양 메뉴입니다.");
							server.selectDog(dog);
							System.out.println("입양하실 유기견의 정보를 입력해주세요.");
							System.out.print("공고번호 >> ");
							int sNum = sc.nextInt();

							System.out.print("품종 >> ");
							String sBreed = sc.next();

							System.out.print("성별 >> ");
							String sGender = sc.next();

							server.dogAdop(sNum, sBreed, sGender);
							break;

						case 2: // 조회
							boolean search = true;
							while (search) {
								System.out.println("유기견 조회 메뉴입니다.");
								System.out.println("================================");
								System.out.println("1.전체조회  2.품종조회  3.성별조회  4.뒤로");
								System.out.println("================================");
								System.out.print("메뉴를 선택하시오 >> ");
								int menu3 = sc.nextInt();

								switch (menu3) {

								case 1:
									System.out.println("전체 조회를 선택하셨습니다.");
									System.out.println();
									server.selectDog(dog);
									break;

								case 2:
									System.out.println("품종별 조회를 선택하셨습니다.");
									System.out.println();
									System.out.print("조회하실 품종 >> ");
									String searchBreed = sc.next();
									server.dogListB(searchBreed);
									break;

								case 3:
									System.out.println("성별 조회를 선택하셨습니다.");
									System.out.println();
									System.out.print("조회하실 성별(여아,남아) >> ");
									String searchGender = sc.next();
									server.dogListG(searchGender);
									break;

								case 4:
									search = false;
									System.out.println("뒤로가기를 누르셨습니다.");
									System.out.println();
									break;

								default:
									System.out.println("메뉴를 다시 선택해주세요!");
									System.out.println();
									break;
								} // end switch(menu3)

							} // end while(search)
							break;

						case 3: // 후원
							System.out.println("유기견 후원 메뉴입니다.");
							System.out.print("입급하실 분의 성함을 입력해주세요 >> ");
							String spName = sc.next();

							System.out.print("후원하실 금액을 입력해주세요 >> ");
							int sponsor = sc.nextInt();

							shelter.setSponsor(sponsor);
							shelter.setsId(sId);
							shelter.setsName(spName);

							server.deposit(shelter);

							System.out.println(spName + "님이 후원하신 금액은 " + sponsor + "원 입니다.");
							System.out.println("후원해주셔서 감사합니다!");
							break;

						case 4: // 로그아웃
							run = false;
							System.out.println("로그아웃 되었습니다.");
							System.out.println();
							break;

						default:
							System.out.println("다시 메뉴를 선택해주세요.");
							System.out.println();
							break;
						} // end switch

					} // end while

				} else { // check == false
					System.out.println("아이디와 비밀번호를 다시 확인바랍니다.");
					System.out.println();

				}
				break;

			case 2:

				System.out.println("회원정보 입력");
				System.out.print("아이디 >>");
				sId = sc.next();
				shelter.setsId(sId);

				System.out.print("비밀번호 >>");
				sPw = sc.next();
				System.out.print("비밀번호 확인 >>");
				String sPwc = sc.next();
				if (sPw.equals(sPwc)) {
					System.out.println("Correct PW");
					shelter.setsPw(sPw);
				} else {
					System.out.println("Incorrect PW");
				}

				System.out.print("이름 >>");
				String sName = sc.next();
				shelter.setsName(sName);

				System.out.print("이메일 >>");
				String sEmail = sc.next();
				shelter.setsEmail(sEmail);

				System.out.print("전화번호 >>");
				String sPhone = sc.next();
				shelter.setsPhone(sPhone);

				System.out.print("주소>>");
				String sAddr = sc.next();
				shelter.setsAddr(sAddr);

				int sSpon = 0;
				shelter.setSponsor(sSpon);

				server.memberJoin(shelter);

				break;

			case 3:
				System.out.print("관리자아이디 >>"); // id = admin
				String adminId = sc.next();

				System.out.print("비밀번호 >>");
				String adminPW = sc.next();

				if (adminId.equals("admin")) {
					boolean admin = true;

					while (admin) {

						System.out.println("================================================");
						System.out.println("1.유기견 관리    2. 회원관리    3. 후원금관리   4. 로그아웃");
						System.out.println("================================================");
						System.out.print("원하는 메뉴를 고르세요 >> ");
						int aMenu = sc.nextInt();

						switch (aMenu) {

						case 1:	// 1.유기견관리

							boolean dog1 = true;

							while (dog1) {

								System.out.println("================================================");
								System.out.println("1. 유기견 등록  2. 유기견 삭제 3. 유기견 수정 4. 뒤로가기");
								System.out.println("================================================");
								System.out.print("원하는 메뉴를 고르세요 >> ");
								menu = sc.nextInt();

								switch (menu) {

								case 1:
									boolean run1 = true;

									while (run1) {

										System.out.println("================================");
										System.out.println("1. 등록 정보 입력하기      2. 뒤로가기 ");
										System.out.println("================================");
										System.out.print("메뉴를 선택해주세요 >> ");
										int iMenu = sc.nextInt();

										switch (iMenu) {

										case 1:
											System.out.println("등록할 유기견의 정보를 입력하세요.");

											System.out.print("이름 >> ");
											String dName = sc.next();
											dog.setdName(dName);

											System.out.print("추정나이 >> ");
											String dAge = sc.next();
											dog.setdAge(dAge);

											System.out.print("품종 >> ");
											String dBreed = sc.next();
											dog.setdBreed(dBreed);

											System.out.print("성별 >> ");
											String dGender = sc.next();
											dog.setdGender(dGender);

											System.out.print("접수일자 >> ");
											String dDate = sc.next();
											dog.setdDate(dDate);

											server.register(dog);
											break;

										case 2:
											run1 = false;
											System.out.println("이전 메뉴로 돌아갑니다.");
											System.out.println();
											break;
										} // iMenu end switch

									} // run1 end while
									break;

								case 2:
									boolean run2 = true;

									while (run2) {

										System.out.println("================================");
										System.out.println("1. 삭제할 유기견 선택      2. 뒤로가기 ");
										System.out.println("================================");
										System.out.print("메뉴를 선택해주세요 >> ");
										int dMenu = sc.nextInt();

										switch (dMenu) {

										case 1:
											server.selectDog(dog);
											System.out.print("삭제하실 유기견의 공고를 입력해주세요 >> ");
											int num = sc.nextInt();

											if (num == dog.getdNo()) {
												dog.setdNo(num);
												server.deleteDog(dog);
											} else {
												System.out.println("유효하지 않은 공고번호 입니다.");
												System.out.println();
											}
											break;

										case 2:
											run2 = false;
											System.out.println("이전메뉴로 돌아갑니다.");
											System.out.println();
											break;

										default:
											System.out.println("입력 값을 다시 확인해주세요.");
											System.out.println();
											break;

										} // dMenu end switch

									} // run2 end while
									break;
								case 3:
									boolean run3 = true;

									while (run3) {

										System.out.println("================================");
										System.out.println("1. 수정할 유기견 선택      2. 뒤로가기 ");
										System.out.println("================================");
										System.out.print("메뉴를 선택해주세요 >> ");
										int uMenu = sc.nextInt();

										switch (uMenu) {

										case 1:
											server.selectDog(dog);
											System.out.println("=================================");
											System.out.print("수정하실 유기견의 공고번호를 입력해주세요 >> ");
											int dNumber = sc.nextInt();

											if (dNumber == dog.getdNo()) {
												dog.setdNo(dNumber);

												System.out.print("이름 >> ");
												String dogName = sc.next();
												dog.setdName(dogName);

												System.out.print("추정나이 >> ");
												String dogAge = sc.next();
												dog.setdAge(dogAge);

												System.out.print("품종 >> ");
												String dogBreed = sc.next();
												dog.setdBreed(dogBreed);

												System.out.print("성별 >> ");
												String dogGender = sc.next();
												dog.setdGender(dogGender);

												System.out.print("접수일자 >> ");
												String dogDate = sc.next();
												dog.setdDate(dogDate);

												server.updateDog(dog);
											} else {
												System.out.println("유효하지 않는 공고번호 입니다.");
												System.out.println();
											}
											break;

										case 2:
											run3 = false;
											System.out.println("이전 메뉴로 돌아갑니다.");
											System.out.println();
											break;

										default:
											System.out.println("입력 값을 다시 확인해주세요.");
											System.out.println();
											break;
										} // uMenu end switch

									} // run3 end while
									break;

								case 4:
									dog1 = false;
									System.out.println("이전 메뉴로 돌아갑니다.");
									System.out.println();
									break;

								default:
									System.out.println("번호를 다시 확인해주세요.");
									System.out.println();
									break;
								} // end switch

							} // end while
							break; // 유기견 관리 끝
						case 2:
							boolean op4 = true;

							while (op4) {
								System.out.println("================== 회원관리 ====================");
								System.out.println("1. 회원조회   2. 회원 삭제   3. 회원정보 수정   4. 뒤로가기");
								System.out.println("==============================================");
								System.out.print("메뉴를 선택 하세오 >> ");
								int member = sc.nextInt();

								switch (member) {

								case 1:
									System.out.println("------회원목록-------");
									server.memberList(shelter);
									break;

								case 2:
									System.out.print("삭제하기 위해 아이디를 입력해주세요 >> ");
									String delId = sc.next();

									boolean memCheck = server.memCheck(delId);

									if (memCheck) {
										server.deleteId(delId);
									} else {
										System.out.println("존재하지 않는 아이디 입니다.");
									}
									break;

								case 3:
									System.out.print("정보를 수정할 회원 아이디를 입력해주세요 >> ");
									String uId = sc.next();

									boolean memCheck2 = server.memCheck2(uId);

									if (memCheck2) {
										shelter.setsId(uId);

										System.out.print("비밀번호 >> ");
										String sPw1 = sc.next();
										shelter.setsPw(sPw1);

										System.out.print("이름 >> ");
										String sName1 = sc.next();
										shelter.setsName(sName1);

										System.out.print("주소 >> ");
										String sAddr1 = sc.next();
										shelter.setsAddr(sAddr1);

										System.out.print("연락처 >> ");
										String sPhone1 = sc.next();
										shelter.setsPhone(sPhone1);

										System.out.print("email >> ");
										String sEmail1 = sc.next();
										shelter.setsEmail(sEmail1);

										server.updateShelter(shelter);
									} else {
										System.out.println("존재하지 않는 아이디 입니다.");
									}
									break;

								case 4:
									op4 = false;
									System.out.println("이전 메뉴로 돌아갑니다.");
									break;

								default:
									System.out.println("Re-enter the menu.");
									break;
								}

							} // switch op4 while //회원관리
							break;

						case 3:
							boolean bonus = true;

							while (bonus) {

								System.out.println("============================");
								System.out.println("1. 후원금 조회       2.뒤로가기");
								System.out.println("============================");
								System.out.print("원하는 메뉴를 고르세요 >> ");
								int bMenu = sc.nextInt();

								if (bMenu == 1) {
									System.out.println("===================================");
									System.out.println("             후원자 명단              ");
									server.sponList(shelter);
									server.sponSum(shelter);
								} else if (bMenu == 2) {
									bonus = false;
									System.out.println("이전 메뉴로 돌아갑니다.");
								} else {
									System.out.println("확인 후 다시 입력해주세요.");
								}

							} // end while

							break; // 후원금 관리 끝

						case 4:
							admin = false;
							System.out.println("로그아웃 되었습니다.");
							break;
						} // end switch

					} // end while
				} else {

					System.out.println("관리자가 아닙니다.");
				}

				break;
			case 4:
				op = false;
				System.out.println("이용해 주셔서 감사합니다.");
				break;
			default:
				System.out.println("다시 메뉴를 선택해주세요.");
				break;
			}
			server.conClose(); // DB disconnect
		}
	}
}
