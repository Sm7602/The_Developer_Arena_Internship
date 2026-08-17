package week_1_task_project;

public class ValidationUtils {
	
	  public static boolean isValidName(String name) {
	        return name != null && !name.trim().isEmpty();
	    }

	    public static boolean isValidAge(byte age) {
	        return age >= 5 && age <= 100;
	    }

	    public static boolean isValidGrade(char grade) {
	        grade = Character.toUpperCase(grade);

	        return grade == 'A' ||
	               grade == 'B' ||
	               grade == 'C' ||
	               grade == 'D' ||
	               grade == 'F';
	    }

	    public static boolean isValidStudentId(String studentId) {
	        return studentId != null && !studentId.trim().isEmpty();
	    }

	    public static boolean isValidContact(String contact) {
	        return contact != null && contact.matches("\\d{10}");
	    }

}
