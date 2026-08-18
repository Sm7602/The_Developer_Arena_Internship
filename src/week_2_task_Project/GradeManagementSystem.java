package week_2_task_Project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Grade Management System
 *
 * Main class responsible for:
 * - Displaying the application menu
 * - Adding students
 * - Viewing students
 * - Searching students
 * - Viewing individual reports
 * - Viewing class performance
 * - Finding top and lowest performers
 */
public class GradeManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<StudentGrade> students = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("       WELCOME TO GRADE MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        loadSampleData();

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

            case 1:addStudent();
                break;

            case 2: viewAllStudents();
                break;

            case 3:searchStudent();
                break;

            case 4:viewStudentReport();
                break;

            case 5:displayClassSummary();
                break;

            case 6:findHighestPerformer();
                break;

            case 7:findLowestPerformer();
                break;

            case 8:displayStudentRanking();
                break;

            case 9:displaySubjectPerformance();
                break;

            case 10:
                running = false;
                exitApplication();
                break;

            default:
                System.out.println(
                        "\nInvalid choice!"
                );

                System.out.println(
                        "Please select an option between 1 and 10."
                );
        }
    }

        scanner.close();
    }

    // =========================================================
    // MENU
    // =========================================================

    private static void displayMenu() {

        System.out.println();
        System.out.println("====================================================");
        System.out.println("                    MAIN MENU");
        System.out.println("====================================================");

        System.out.println("1.  Add Student");
        System.out.println("2.  View All Students");
        System.out.println("3.  Search Student");
        System.out.println("4.  View Student Report");
        System.out.println("5.  Class Performance Summary");
        System.out.println("6.  Find Highest Performer");
        System.out.println("7.  Find Lowest Performer");
        System.out.println("8.  Student Ranking");
        System.out.println("9.  Subject Performance");
        System.out.println("10. Exit");

        System.out.println("====================================================");
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    private static void addStudent() {

        System.out.println("\n==============================================");
        System.out.println("              ADD NEW STUDENT");
        System.out.println("==============================================");

        String studentId;

        while (true) {

            studentId = readString("Enter Student ID: ");

            if (isStudentIdExists(studentId)) {
                System.out.println("Student ID already exists!");
                System.out.println("Please enter a different ID.");
            } else {
                break;
            }
        }

        String studentName;

        while (true) {

            studentName = readString("Enter Student Name: ");

            if (studentName.isEmpty()) {
                System.out.println("Student name cannot be empty.");
            } else {
                break;
            }
        }

        int subjectCount;

        while (true) {

            subjectCount = readInt("Enter number of subjects: ");

            if (subjectCount <= 0) {
                System.out.println("Number of subjects must be greater than 0.");
            } else {
                break;
            }
        }

        String[] subjects = new String[subjectCount];
        double[] marks = new double[subjectCount];

        for (int i = 0; i < subjectCount; i++) {
            System.out.println("\nSubject " + (i + 1));
            subjects[i] = readString("Enter subject name: ");
            marks[i] = readMarks("Enter marks for " + subjects[i] + " (0-100): ");
        }

        StudentGrade student = new StudentGrade(studentId,studentName,subjects,marks);
        students.add(student);

        System.out.println("\nStudent added successfully!");
        System.out.println("----------------------------------------------");
        System.out.println("Student ID   : " + studentId);
        System.out.println("Student Name : " + studentName);
        System.out.println("Subjects     : " + subjectCount);
        System.out.println("----------------------------------------------");
    }

    // =========================================================
    // VIEW ALL STUDENTS
    // =========================================================

    private static void viewAllStudents() {

        System.out.println("\n==============================================");
        System.out.println("              ALL STUDENTS");
        System.out.println("==============================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.printf(
                "%-12s %-20s %-12s %-10s %-10s%n",
                "Student ID",
                "Name",
                "Average",
                "Grade",
                "Status"
        );

        System.out.println("----------------------------------------------------------------");

        for (StudentGrade student : students) {
            double average = GradeCalculator.calculateAverage(student.getMarks() );
            String grade = GradeCalculator.calculateGrade(average);
            String status = average >= 40 ? "PASS" : "FAIL";
            System.out.printf(
                    "%-12s %-20s %-12.2f %-10s %-10s%n",
                    student.getStudentId(),
                    student.getStudentName(),
                    average,
                    grade,
                    status
            );
        }
    }

    // =========================================================
    // SEARCH STUDENT
    // =========================================================

    private static void searchStudent() {

        System.out.println("\n==============================================");
        System.out.println("             SEARCH STUDENT");
        System.out.println("==============================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        String keyword = readString("Enter Student ID or Name: " );
        boolean found = false;

        for (StudentGrade student : students) {

            if (student.getStudentId().equalsIgnoreCase(keyword)
                    || student.getStudentName().toLowerCase().contains(keyword.toLowerCase())) {
                displayStudentInformation(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo student found with: " + keyword);
        }
    }

    // =========================================================
    // STUDENT REPORT
    // =========================================================

    private static void viewStudentReport() {

        System.out.println("\n==============================================");
        System.out.println("          STUDENT PERFORMANCE REPORT");
        System.out.println("==============================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        String studentId = readString("Enter Student ID: ");
        StudentGrade student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        ReportGenerator.generateStudentReport(student);
    }

    // =========================================================
    // CLASS SUMMARY
    // =========================================================

    private static void displayClassSummary() {

        System.out.println("\n==============================================");
        System.out.println("          CLASS PERFORMANCE SUMMARY");
        System.out.println("==============================================");

        if (students.isEmpty()) {

            System.out.println("No student records available.");
            return;
        }

        ReportGenerator.generateClassSummary(students);
    }

    // =========================================================
    // HIGHEST PERFORMER
    // =========================================================

    private static void findHighestPerformer() {

        System.out.println("\n==============================================");
        System.out.println("            HIGHEST PERFORMER");
        System.out.println("==============================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        StudentGrade highestStudent = students.get(0);

        double highestAverage = GradeCalculator.calculateAverage(highestStudent.getMarks());

        for (StudentGrade student : students) {

            double average = GradeCalculator.calculateAverage(student.getMarks());

            if (average > highestAverage) {

                highestAverage = average;
                highestStudent = student;
            }
        }

        System.out.println("Student ID   : "+ highestStudent.getStudentId());
        System.out.println("Student Name : "+ highestStudent.getStudentName());
        System.out.printf("Average      : %.2f%n",highestAverage);
        System.out.println("Grade        : "+ GradeCalculator.calculateGrade(highestAverage));
    }

    // =========================================================
    // LOWEST PERFORMER
    // =========================================================

    private static void findLowestPerformer() {

        System.out.println("\n==============================================");
        System.out.println("             LOWEST PERFORMER");
        System.out.println("==============================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        StudentGrade lowestStudent = students.get(0);

        double lowestAverage = GradeCalculator.calculateAverage(lowestStudent.getMarks());

        for (StudentGrade student : students) {

            double average = GradeCalculator.calculateAverage(student.getMarks());

            if (average < lowestAverage) {

                lowestAverage = average;
                lowestStudent = student;
            }
        }

        System.out.println("Student ID   : "+ lowestStudent.getStudentId());
        System.out.println("Student Name : "+ lowestStudent.getStudentName());
        System.out.printf("Average      : %.2f%n", lowestAverage);
        System.out.println("Grade        : "+ GradeCalculator.calculateGrade(lowestAverage));
    }
    
    private static void displayStudentRanking() {

        System.out.println();
        System.out.println("====================================================");
        System.out.println("                 STUDENT RANKING");
        System.out.println("====================================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        ReportGenerator.displayStudentRanking(students);
    }

    // =========================================================
    // SUBJECT PERFORMANCE
    // =========================================================

    private static void displaySubjectPerformance() {

        System.out.println();
        System.out.println("====================================================");
        System.out.println("                SUBJECT PERFORMANCE");
        System.out.println("====================================================");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        ReportGenerator.generateSubjectSummary(students);
    }

    // =========================================================
    // DISPLAY STUDENT INFORMATION
    // =========================================================

    private static void displayStudentInformation(StudentGrade student) {

        double average = GradeCalculator.calculateAverage(student.getMarks());

        double highest = GradeCalculator.findHighestMark(student.getMarks());

        double lowest = GradeCalculator.findLowestMark(student.getMarks());

        String grade = GradeCalculator.calculateGrade(average);

        System.out.println("\n----------------------------------------------");
        System.out.println("Student ID   : "+ student.getStudentId());
        System.out.println("Student Name : "+ student.getStudentName());
        System.out.println("----------------------------------------------");

        String[] subjects = student.getSubjects();
        double[] marks = student.getMarks();

        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-20s : %.2f%n",subjects[i],marks[i]);
        }

        System.out.println("----------------------------------------------");

        System.out.printf("Average      : %.2f%n", average);
        System.out.printf("Highest Mark : %.2f%n", highest);
        System.out.printf("Lowest Mark  : %.2f%n", lowest);
        System.out.println("Grade        : " + grade);
        System.out.println(
                "Status       : " + (average >= 40 ? "PASS" : "FAIL")
        );

        System.out.println("----------------------------------------------");
    }

    // =========================================================
    // FIND STUDENT BY ID
    // =========================================================

    private static StudentGrade findStudentById(String studentId) {
        for (StudentGrade student : students) {

            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    // =========================================================
    // CHECK DUPLICATE STUDENT ID
    // =========================================================

    private static boolean isStudentIdExists(String studentId) {
        for (StudentGrade student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }
        return false;
    }

    // =========================================================
    // INPUT VALIDATION
    // =========================================================

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim()
                );
                } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number."
                );
            }
        }
    }

    private static double readMarks(String message) {

        while (true) {
            System.out.print(message);
            try {
                double marks = Double.parseDouble(scanner.nextLine().trim()
                );
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks! Marks must be between 0 and 100.");
                } else {

                    return marks;
                }

            } catch (NumberFormatException e) {
                System.out.println( "Invalid input! Please enter a valid number.");
            }
        }
    }

    private static String readString(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again." );
        }
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    private static void loadSampleData() {

        String[] subjects1 = {"Java","Database","Spring Boot","HTML"};
        double[] marks1 = {85,78,92,81};
        students.add(new StudentGrade( "STU001","Rahul",subjects1,marks1));

        String[] subjects2 = {"Java","Database","Spring Boot","HTML"};
        double[] marks2 = {92,88, 95,90};
        students.add(new StudentGrade("STU002","Priya",subjects2,marks2));

        String[] subjects3 = {"Java","Database","Spring Boot","HTML" };
        double[] marks3 = {65,72,68,70};
        students.add(new StudentGrade("STU003", "Amit",subjects3,marks3));
    }
    
    private static void exitApplication() {

        System.out.println();
        System.out.println("====================================================");
        System.out.println("       Thank You For Using Grade Management System");
        System.out.println("====================================================");
        System.out.println("Application closed successfully.");
    }
}
