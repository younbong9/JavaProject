package dogShelter;	// donate

public class ShelterDTO3 {
	private String dName;	// 후원자
	private int donation;	// 기부금
	
	// getters and setters
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public int getDonation() {
		return donation;
	}
	public void setDonation(int donation) {
		this.donation = donation;
	}
	
	// toString
	@Override
	public String toString() {
		return "ShelterDTO3 [dName=" + dName + ", donation=" + donation + "]";
	}
	
	// 기본 생성자
	public ShelterDTO3() {
		super();
	}
	
	// 매개변수 생성자
	public ShelterDTO3(String dName, int donation) {
		super();
		this.dName = dName;
		this.donation = donation;
	}
	
}
