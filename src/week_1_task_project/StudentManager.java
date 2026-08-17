package week_1_task_project;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
	
	 private List<Student> students = new ArrayList<>();
	 
	   public boolean addStudent(Student student) {

	        if (findStudentById(student.getStudentId()) != null) {
	            return false;
	        }

	        students.add(student);
	        return true;
	    }

	    public List<Student> getAllStudents() {
	        return students;
	    }
	 
	 public Student findStudentById(String id) {
	        for (Student student : students) {
	            if (student.getStudentId() == id) {
	                return student;
	            }
	        }

	        return null;
	    }

	    public boolean deleteStudent(String id) {
	        Student student = findStudentById(id);
	        if (student != null) {
	            students.remove(student);
	            return true;
	        }

	        return false;
	    }
	    
	    public boolean updateStudent(
	            String studentId,
	            String name,
	            byte age,
	            char grade,
	            String contact) {

	        Student student = findStudentById(studentId);

	        if (student == null) {
	            return false;
	        }

	        student.setName(name);
	        student.setAge(age);
	        student.setGrade(grade);
	        student.setContact(contact);

	        return true;
	    }

}
