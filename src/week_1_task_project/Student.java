package week_1_task_project;

public class Student {
	
	private String name;
	
	private byte age;
	
	private char grade;
	
	private String studentId;

	private String contact;
	
	public Student(String name, byte age, char grade, String studentId, String contact) {
		super();
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.studentId = studentId;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", grade=" + grade + ", studentId=" + studentId + ", contact="
				+ contact + "]";
	}
	
	

}
