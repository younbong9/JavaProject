package dogShelter;	// dog

public class ShelterDTO2 {

	private String sNum;		// 공고번호
	private String sDate;		// 접수일자
	private String sBreed;		// 품종
	private String sGender;		// 성별
	private String sChar;		// 특징
	private String sEstiAge;	// 추정나이
	
	// getters and setters
	public String getsNum() {
		return sNum;
	}
	public void setsNum(String sNum) {
		this.sNum = sNum;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String getsBreed() {
		return sBreed;
	}
	public void setsBreed(String sBreed) {
		this.sBreed = sBreed;
	}
	public String getsGender() {
		return sGender;
	}
	public void setsGender(String sGender) {
		this.sGender = sGender;
	}
	public String getsChar() {
		return sChar;
	}
	public void setsChar(String sChar) {
		this.sChar = sChar;
	}
	public String getsEstiAge() {
		return sEstiAge;
	}
	public void setsEstiAge(String sEstiAge) {
		this.sEstiAge = sEstiAge;
	}
	
	// toString
	@Override
	public String toString() {
		return "ShelterDTO2 [sNum=" + sNum + ", sDate=" + sDate + ", sBreed=" + sBreed + ", sGender=" + sGender
				+ ", sChar=" + sChar + ", sEstiAge=" + sEstiAge + "]";
	}
	
	// 기본 생성자
	public ShelterDTO2() {
		super();
	}
	
	// 매겨변수 생성자
	public ShelterDTO2(String sNum, String sDate, String sBreed, String sGender, String sChar, String sEstiAge) {
		super();
		this.sNum = sNum;
		this.sDate = sDate;
		this.sBreed = sBreed;
		this.sGender = sGender;
		this.sChar = sChar;
		this.sEstiAge = sEstiAge;
	}
}
