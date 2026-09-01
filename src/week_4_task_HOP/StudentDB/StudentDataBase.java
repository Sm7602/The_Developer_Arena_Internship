package week_4_task_HOP.StudentDB;

import java.util.HashMap;
import java.util.Scanner;

public class StudentDataBase {
	
	private final HashMap<Integer, Student> students = new HashMap<>();
	private final Scanner scanner = new Scanner(System.in);
	

	// Add Student
	public void addStudent() { 
		
		System.out.print("Enter student ID: ");
		int id = readInt();
		  if (students.containsKey(id)) { 
			   System.out.println("Student ID already exists."); 
			    return; 
			   } 
		  
		  System.out.print("Enter student name: "); 
		  String name = scanner.nextLine().trim(); 
		       if (name.isEmpty()) {
		    	       System.out.println("Name cannot be empty.");
		    	         return; 
		    	        } 
		       
		   System.out.print("Enter age: "); 
		   int age = readInt();
		          if (age <= 0) { 
		        	     System.out.println("Age must be greater than 0."); 
		        	       return; 
		        	       } 
		          
		   System.out.print("Enter course: "); 
		   String course = scanner.nextLine().trim();
		          if (course.isEmpty()) {
		        	       System.out.println("Course cannot be empty.");
		        	       return; } 
		          
		   System.out.print("Enter marks: "); 
		   double marks = readDouble(); 
		        if (marks < 0 || marks > 100){ 
		        	        System.out.println("Marks must be between 0 and 100."); 
		                return; 
		                }
		        
		        
		 Student student = new Student( id, name, age, course, marks );
		 
		 students.put(id, student); 
		 System.out.println("Student added successfully.");
		 
	}
	
	
	// View All Students 
	public void viewStudents() { 
		
		if (students.isEmpty()) {
			System.out.println("No students found.");
			return; } 
		
		System.out.println("\n========== STUDENT LIST ==========");
		for (Student student : students.values()) {
			System.out.println(student); 
			} 
		
	}
	
	
	// Search Student 
	public void searchStudent() { 
		
		System.out.print("Enter student ID: "); 
		int id = readInt(); 
		Student student = students.get(id); 
		       if (student == null) { 
			       System.out.println("Student not found."); 
			      } else { 
			    	      System.out.println("\nStudent Found:");
			    	      System.out.println(student); 
			    	      } 
		       
	}
	
	
	// Update Student
	public void updateStudent() {
		System.out.print("Enter student ID to update: ");
		int id = readInt();
		Student student = students.get(id); 
		      if (student == null) {
		    	  System.out.println("Student not found.");
		    	    return; 
		    	    } 
		      
		System.out.print("Enter new name: ");
		String name = scanner.nextLine().trim();
		    if (name.isEmpty()) { 
		    	   System.out.println("Name cannot be empty."); 
		    	   return; 
		    	   } 
		
		System.out.print("Enter new age: "); 
		int age = readInt(); 
		     if (age <= 0) {
		    	    System.out.println("Age must be greater than 0.");
		    	    return; 
		    	    } 
		     
		System.out.print("Enter new course: ");
		String course = scanner.nextLine().trim(); 
		     if (course.isEmpty()) { 
		    	     System.out.println("Course cannot be empty."); 
		    	     return; 
		    	     }
		     
		System.out.print("Enter new marks: ");
		double marks = readDouble(); 
		    if (marks < 0 || marks > 100) {
		    	     System.out.println("Marks must be between 0 and 100."); 
		    	     return;
		    	     }
		    
		student.setName(name);
		student.setAge(age);
		student.setCourse(course);
		student.setMarks(marks); 
		System.out.println("Student updated successfully."); 
		
	}
	
	
	// Delete Student 
	public void deleteStudent() {
		
		System.out.print("Enter student ID to delete: ");
		int id = readInt(); 
		Student removedStudent = students.remove(id); 
		    if (removedStudent == null) { 
		    	       System.out.println("Student not found."); 
		    	       } else {
		    	    	      System.out.println("Student deleted successfully.");
		    	    	      } 
		    
	}
	
	
	// Display Number of Students
	public void studentCount() { 
		
		System.out.println("Total students: " + students.size());	
	}
	
	
	// Menu 
	public void showMenu() { 
		
		while (true) {
			System.out.println("\n========== STUDENT DATABASE ==========");
			System.out.println("1. Add Student"); 
			System.out.println("2. View All Students"); 
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Student Count"); 
			System.out.println("7. Exit"); 
			System.out.println("======================================"); 
			System.out.print("Enter your choice: ");
			
			String choice = scanner.nextLine();
			switch (choice) { 
			             case "1": 
			            	         addStudent();
			            	         break; 
			            	         
			             case "2": 
			            	         viewStudents();
			            	         break; 
			            	         
			             case "3": 
			            	         searchStudent(); 
			            	         break; 
			            	         
			             case "4": 
			            	          updateStudent(); 
			            	          break;
			            	          
			             case "5":
			            	         deleteStudent();
			            	         break; 
			            	         
			             case "6": 
			            	         studentCount(); 
			            	         break;
			            	         
			             case "7":
			            	          System.out.println("Thank you for using Student Database.");
			            	          scanner.close(); 
			            	          return; 
			            	          
			             default: System.out.println("Invalid choice. Please try again."); 
			             } 
			} 	
	}
	
	
    private int readInt() {
		
		while (true) { 
			try { 
				return Integer.parseInt(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.print("Please enter a valid integer: ");
					} 
			}	
	}
	
	private double readDouble() { 
		
		while (true) { 
			try {
				return Double.parseDouble(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.print("Please enter a valid number: "); 
					} 
			}
	}
}
