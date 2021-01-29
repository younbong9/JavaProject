package dogShelter2;

public class ShelterDTO {
	private String sId;
	private String sPw;
	private String sName;
	private String sAddr;
	private String sPhone;
	private String sEmail;
	private String charityNo;
	private String cMoney;
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsPw() {
		return sPw;
	}
	public void setsPw(String sPw) {
		this.sPw = sPw;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsAddr() {
		return sAddr;
	}
	public void setsAddr(String sAddr) {
		this.sAddr = sAddr;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	public String getCharityNo() {
		return charityNo;
	}
	public void setCharityNo(String charityNo) {
		this.charityNo = charityNo;
	}
	public String getcMoney() {
		return cMoney;
	}
	public void setcMoney(String cMoney) {
		this.cMoney = cMoney;
	}
	

	@Override
	public String toString() {
		return "ShelterDTO [sId=" + sId + ", sPw=" + sPw + ", sName=" + sName + ", sAddr=" + sAddr + ", sPhone="
				+ sPhone + ", sEmail=" + sEmail + ", charityNo=" + charityNo + ", cMoney=" + cMoney + "]";
	}
	
	public ShelterDTO(String sId, String sPw, String sName, String sAddr, String sPhone, String sEmail,
			String charityNo, String cMoney) {
		this.sId = sId;
		this.sPw = sPw;
		this.sName = sName;
		this.sAddr = sAddr;
		this.sPhone = sPhone;
		this.sEmail = sEmail;
		this.charityNo = charityNo;
		this.cMoney = cMoney;
	}
	public ShelterDTO() {
	
	}

	
}
