package JH_dogShelter;

public class ShelterDTO2 {
	private int dNo;
	private String dName;
	private String dAge;
	private String dBreed;
	private String dGender;
	private String dDate;
	
	// getter, setter
	public int getdNo() {
		return dNo;
	}
	public void setdNo(int dNo) {
		this.dNo = dNo;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdAge() {
		return dAge;
	}
	public void setdAge(String dAge) {
		this.dAge = dAge;
	}
	public String getdBreed() {
		return dBreed;
	}
	public void setdBreed(String dBreed) {
		this.dBreed = dBreed;
	}
	public String getdGender() {
		return dGender;
	}
	public void setdGender(String dGender) {
		this.dGender = dGender;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	
	// toString
	@Override
	public String toString() {
		return "ShelterDAO2 [dNo=" + dNo + ", dName=" + dName + ", dAge=" + dAge + ", dBreed=" + dBreed + ", dGender="
				+ dGender + ", dDate=" + dDate + "]";
	}
	
	// 기본 생성자
	public ShelterDTO2() {
		super();
	}
	
	// 매개변수 생성자
	public ShelterDTO2(int dNo, String dName, String dAge, String dBreed, String dGender, String dDate) {
		super();
		this.dNo = dNo;
		this.dName = dName;
		this.dAge = dAge;
		this.dBreed = dBreed;
		this.dGender = dGender;
		this.dDate = dDate;
	}
	
	
	
	
}