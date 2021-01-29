package TNaver;

public class NaverDTO {
	
	// 회원가입 정보 필드
		private String nId;		// 아이디
		private String nPw;		// 비밀번호
		private String nName;	// 이름
		private String nBirth;	// 생년월일
		private String nGender;	// 성별
		private String nEmail;	// 이메일
		private String nPhone;	// 휴대전화
		
		// getter, setter
		public String getnId() {
			return nId;
		}
		public void setnId(String nId) {
			this.nId = nId;
		}
		public String getnPw() {
			return nPw;
		}
		public void setnPw(String nPw) {
			this.nPw = nPw;
		}
		public String getnName() {
			return nName;
		}
		public void setnName(String nName) {
			this.nName = nName;
		}
		public String getnBirth() {
			return nBirth;
		}
		public void setnBirth(String nBirth) {
			this.nBirth = nBirth;
		}
		public String getnGender() {
			return nGender;
		}
		public void setnGender(String nGender) {
			this.nGender = nGender;
		}
		public String getnEmail() {
			return nEmail;
		}
		public void setnEmail(String nEmail) {
			this.nEmail = nEmail;
		}
		public String getnPhone() {
			return nPhone;
		}
		public void setnPhone(String nPhone) {
			this.nPhone = nPhone;
		}
		
		// toString()
		@Override
		public String toString() {
			return "NaverDTO [nId=" + nId + ", nPw=" + nPw + ", nName=" + nName + ", nBirth=" + nBirth + ", nGender="
					+ nGender + ", nEmail=" + nEmail + ", nPhone=" + nPhone + "]";
		}
		
		// 기본생성자
		public NaverDTO() {
			super();
		}
		
		// 매개변수생성자
		public NaverDTO(String nId, String nPw, String nName, String nBirth, String nGender, String nEmail, String nPhone) {
			super();
			this.nId = nId;
			this.nPw = nPw;
			this.nName = nName;
			this.nBirth = nBirth;
			this.nGender = nGender;
			this.nEmail = nEmail;
			this.nPhone = nPhone;
		}
}
