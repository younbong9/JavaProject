package dogShelter2;

public class DogDTO {
	private String dNo;
	private String dName;
	private int dAge;
	private String dBreed;
	private String dSex;
	private String dRegitDate;
	public String getdNo() {
		return dNo;
	}
	public void setdNo(String dNo) {
		this.dNo = dNo;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public int getdAge() {
		return dAge;
	}
	public void setdAge(int dAge) {
		this.dAge = dAge;
	}
	public String getdBreed() {
		return dBreed;
	}
	public void setdBreed(String dBreed) {
		this.dBreed = dBreed;
	}
	public String getdSex() {
		return dSex;
	}
	public void setdSex(String dSex) {
		this.dSex = dSex;
	}
	public String getdRegitDate() {
		return dRegitDate;
	}
	public void setdRegitDate(String dRegitDate) {
		this.dRegitDate = dRegitDate;
	}
	@Override
	public String toString() {
		return "DogDTO [dNo=" + dNo + ", dName=" + dName + ", dAge=" + dAge + ", dBreed=" + dBreed + ", dSex=" + dSex
				+ ", dRegitDate=" + dRegitDate + "]";
	}
	public DogDTO(String dNo, String dName, int dAge, String dBreed, String dSex, String dRegitDate) {
		this.dNo = dNo;
		this.dName = dName;
		this.dAge = dAge;
		this.dBreed = dBreed;
		this.dSex = dSex;
		this.dRegitDate = dRegitDate;
	}
	public DogDTO() {
	}
	
}
