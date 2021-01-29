package Jdbc0120;

// DTO(Data Transfer Object)
// : 데이터 전송 객체
// : 클래스 필드, 메소드, 생성자
public class StuDTO {
	
	// (1) 필드
	private String stuName;
	private int stuAge;
	
	
	// getter, setter, 생성자, toString 만들어보기
	// (2) 메소드
	// getters and setters
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
		
	
	// toString : 출력을 도와주는 메소드
	@Override
	public String toString() {
		return "StuDTO [stuName=" + stuName + ", stuAge=" + stuAge + "]";
	}
		
	
	// (3) 생성자	
	// 기본생성자
	public StuDTO() {
		super();
	}
	
	// 매개변수(파라미터) 생성자
	public StuDTO(String stuName, int stuAge) {
		super();
		this.stuName = stuName;
		this.stuAge = stuAge;
	} 
	
	
	

	
	
	
	
	
}
