package week_3_task_HOP;

class StudentClass {
	
	private int studentId;
	
	private String studentName;
	
	private String studentAddress;
	
	private int studentAge;
	
	
	
	public StudentClass(int studentId, String studentName, String studentAddress, int studentAge) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentAge = studentAge;
	}



	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public String getStudentAddress() {
		return studentAddress;
	}



	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}



	public int getStudentAge() {
		return studentAge;
	}



	public void setStudentAge(byte studentAge) {
		this.studentAge = studentAge;
	}



	public void studentPlaying() {
		System.out.println("studentPlaying..........");
	}
	
	public void studentEating() {
		System.out.println("studentEating..........");
	}



	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentAddress=" + studentAddress
				+ ", studentAge=" + studentAge + "]";
	}

}

public class Student {
	public static void main(String[] arg) {
	StudentClass sc=new StudentClass(1,"souvik","delhi",21);
	System.out.println(sc.toString());
	sc.studentEating();
	}
}
