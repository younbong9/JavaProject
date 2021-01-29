package Naver;

public class NaverDTO {
				// DTO : Data Transfer Object(DVO 또는 Bins)
				// 데이터 전송 걕체
				// 메인함수 -> DTO -> DAO -> ...
	
	// (1) 필드
	private String 	naverId;
	private String 	naverPw;
	private String 	naverName;
	private int		naverBirth;
	private String 	naverXy;
	private String 	naverMail;
	private String 	naverPhone;
	
	
	// (2) 메소드
	public String getNaverId() {
		return naverId;
	}
	public void setNaverId(String naverId) {
		this.naverId = naverId;
	}
	public String getNaverPw() {
		return naverPw;
	}
	public void setNaverPw(String naverPw) {
		this.naverPw = naverPw;
	}
	public String getNaverName() {
		return naverName;
	}
	public void setNaverName(String naverName) {
		this.naverName = naverName;
	}
	public int getNaverBirth() {
		return naverBirth;
	}
	public void setNaverBirth(int naverBirth) {
		this.naverBirth = naverBirth;
	}
	public String getNaverXy() {
		return naverXy;
	}
	public void setNaverXy(String naverXy) {
		this.naverXy = naverXy;
	}
	public String getNaverMail() {
		return naverMail;
	}
	public void setNaverMail(String naverMail) {
		this.naverMail = naverMail;
	}
	public String getNaverPhone() {
		return naverPhone;
	}
	public void setNaverPhone(String naverPhone) {
		this.naverPhone = naverPhone;
	}	
	
	// toString
	@Override
	public String toString() {
		return "NaverDTO [naverId=" + naverId + ", naverPw=" + naverPw + ", naverName=" + naverName + ", naverBirth="
				+ naverBirth + ", naverXy=" + naverXy + ", naverMail=" + naverMail + ", naverPhone=" + naverPhone + "]";
	}	
	
	
	// (3) 생성자	
	// 기본생성자
	public NaverDTO() {
		super();
	}	
	// 파라미터 생성자
	public NaverDTO(String naverId, String naverPw, String naverName, int naverBirth, String naverXy, String naverMail,
			String naverPhone) {
		super();
		this.naverId = naverId;
		this.naverPw = naverPw;
		this.naverName = naverName;
		this.naverBirth = naverBirth;
		this.naverXy = naverXy;
		this.naverMail = naverMail;
		this.naverPhone = naverPhone;
	}
	
	
	
	
	
	

}
