package week_1_task_project;

import java.util.Scanner;

public class StudentInformationSystem {
	
	 public static void main(String[] args) {

	        Scanner scanner = new Scanner(System.in);
	        StudentManager manager = new StudentManager();

	        while (true) {

	            System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
	            System.out.println("1. Add Student");
	            System.out.println("2. View All Students");
	            System.out.println("3. Search Student");
	            System.out.println("4. Update Student");
	            System.out.println("5. Delete Student");
	            System.out.println("6. Exit");

	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {

	                case 1:

	                    System.out.print("Enter Student ID: ");
	                    String studentId = scanner.nextLine();

	                    if (!ValidationUtils.isValidStudentId(studentId)) {
	                        System.out.println("Invalid Student ID.");
	                        break;
	                    }

	                    System.out.print("Enter Name: ");
	                    String name = scanner.nextLine();

	                    if (!ValidationUtils.isValidName(name)) {
	                        System.out.println("Invalid Name.");
	                        break;
	                    }

	                    System.out.print("Enter Age: ");
	                    byte age = scanner.nextByte();

	                    if (!ValidationUtils.isValidAge(age)) {
	                        System.out.println("Invalid Age.");
	                        break;
	                    }

	                    scanner.nextLine();

	                    System.out.print("Enter Grade: ");
	                    char grade = scanner.nextLine().charAt(0);

	                    grade = Character.toUpperCase(grade);

	                    if (!ValidationUtils.isValidGrade(grade)) {
	                        System.out.println("Invalid Grade.");
	                        break;
	                    }

	                    System.out.print("Enter Contact: ");
	                    String contact = scanner.nextLine();

	                    if (!ValidationUtils.isValidContact(contact)) {
	                        System.out.println("Contact must contain 10 digits.");
	                        break;
	                    }

	                    Student student = new Student(name,age,grade,studentId,contact);

	                    if (manager.addStudent(student)) {
	                        System.out.println("Student added successfully.");
	                    } else {
	                        System.out.println("Student ID already exists.");
	                    }

	                    break;

	                case 2:

	                    if (manager.getAllStudents().isEmpty()) {
	                        System.out.println("No students found.");
	                    } else {

	                        System.out.println("\n----- Student List -----");

	                        for (Student s : manager.getAllStudents()) {
	                            System.out.println(s);
	                        }
	                    }

	                    break;

	                case 3:

	                    System.out.print("Enter Student ID: ");
	                    String searchId = scanner.nextLine();

	                    Student foundStudent =
	                            manager.findStudentById(searchId);

	                    if (foundStudent != null) {
	                        System.out.println(foundStudent);
	                    } else {
	                        System.out.println("Student not found.");
	                    }

	                    break;

	                case 4:

	                    System.out.print("Enter Student ID: ");
	                    String updateId = scanner.nextLine();

	                    Student existingStudent =
	                            manager.findStudentById(updateId);

	                    if (existingStudent == null) {
	                        System.out.println("Student not found.");
	                        break;
	                    }

	                    System.out.print("Enter New Name: ");
	                    String newName = scanner.nextLine();

	                    System.out.print("Enter New Age: ");
	                    byte newAge = scanner.nextByte();
	                    scanner.nextLine();

	                    System.out.print("Enter New Grade: ");
	                    char newGrade = scanner.nextLine().charAt(0);
	                    newGrade = Character.toUpperCase(newGrade);

	                    System.out.print("Enter New Contact: ");
	                    String newContact = scanner.nextLine();

	                    if (!ValidationUtils.isValidName(newName) ||
	                        !ValidationUtils.isValidAge(newAge) ||
	                        !ValidationUtils.isValidGrade(newGrade) ||
	                        !ValidationUtils.isValidContact(newContact)) {

	                        System.out.println("Invalid student information.");
	                        break;
	                    }

	                    manager.updateStudent(updateId,newName,newAge,newGrade,newContact);

	                    System.out.println("Student updated successfully.");

	                    break;

	                case 5:

	                    System.out.print("Enter Student ID: ");
	                    String deleteId = scanner.nextLine();

	                    if (manager.deleteStudent(deleteId)) {
	                        System.out.println("Student deleted successfully.");
	                    } else {
	                        System.out.println("Student not found.");
	                    }

	                    break;

	                case 6:

	                    System.out.println("Thank you for using Student Information System.");
	                    scanner.close();
	                    return;

	                default:

	                    System.out.println("Invalid choice.");
	            }
	        }
	    }

}
