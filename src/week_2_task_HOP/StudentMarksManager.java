package week_2_task_HOP;

import java.util.Scanner;

public class StudentMarksManager {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Student information
        System.out.println("======================================");
        System.out.println("       STUDENT MARKS MANAGER");
        System.out.println("======================================");

        System.out.print("Enter student name: ");
        String studentName = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int numberOfSubjects = sc.nextInt();
        sc.nextLine();

        String[] subjects = new String[numberOfSubjects];
        int[] marks = new int[numberOfSubjects];

        // Input subject names and marks
        for (int i = 0; i < numberOfSubjects; i++) {

            System.out.print("\nEnter subject " + (i + 1) + " name: ");
            subjects[i] = sc.nextLine();

            while (true) {
                System.out.print("Enter marks for " + subjects[i] + " (0-100): ");
                int mark = sc.nextInt();

                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Invalid marks! Please enter between 0 and 100.");
                }
            }
        }

        // Calculate total, highest and lowest
        int total = 0;
        int highest = marks[0];
        int lowest = marks[0];
        String highestSubject = subjects[0];
        String lowestSubject = subjects[0];

        for (int i = 0; i < numberOfSubjects; i++) {

            total += marks[i];

            if (marks[i] > highest) {
                highest = marks[i];
                highestSubject = subjects[i];
            }

            if (marks[i] < lowest) {
                lowest = marks[i];
                lowestSubject = subjects[i];
            }
        }

        double percentage = (double) total / numberOfSubjects;

        // Grade calculation
        String grade;

        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 50) {
            grade = "D";
        } else if (percentage >= 40) {
            grade = "E";
        } else {
            grade = "F";
        }

        // Result
        String result = percentage >= 40 ? "PASS" : "FAIL";

        // Display report
        System.out.println("\n\n======================================");
        System.out.println("          STUDENT MARKS REPORT");
        System.out.println("======================================");

        System.out.println("Student Name : " + studentName);

        System.out.println("--------------------------------------");

        System.out.printf("%-20s %s%n", "Subject", "Marks");
        System.out.println("--------------------------------------");

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.printf("%-20s %d%n", subjects[i], marks[i]);
        }

        System.out.println("--------------------------------------");

        System.out.println("Total Marks  : " + total);
        System.out.printf("Percentage   : %.2f%%%n", percentage);
        System.out.println("Grade        : " + grade);
        System.out.println("Result       : " + result);

        System.out.println("--------------------------------------");

        System.out.println("Highest Marks: " + highest +
                " (" + highestSubject + ")");

        System.out.println("Lowest Marks : " + lowest +
                " (" + lowestSubject + ")");

        System.out.println("======================================");

        // Subject search
        sc.nextLine();

        System.out.print("\nEnter a subject name to search: ");
        String searchSubject = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < numberOfSubjects; i++) {

            if (subjects[i].equalsIgnoreCase(searchSubject)) {

                System.out.println("\nSubject Found!");
                System.out.println("Subject : " + subjects[i]);
                System.out.println("Marks   : " + marks[i]);

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Subject not found.");
        }

        System.out.println("\nThank you for using Student Marks Manager!");

        sc.close();
    }
}
