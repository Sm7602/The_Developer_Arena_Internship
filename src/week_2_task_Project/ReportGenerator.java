package week_2_task_Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ReportGenerator
 *
 * Responsible for generating:
 * - Individual student reports
 * - Class performance summary
 * - Grade distribution
 * - Pass/Fail statistics
 * - Top performer
 * - Lowest performer
 * - Subject-wise performance
 */
public final class ReportGenerator {

    private ReportGenerator() {
        throw new UnsupportedOperationException("ReportGenerator is a utility class and cannot be instantiated.");
    }

    // =========================================================
    // INDIVIDUAL STUDENT REPORT
    // =========================================================

    /**
     * Generates a detailed report for one student.
     *
     * @param student student object
     */
    public static void generateStudentReport(StudentGrade student) {
        validateStudent(student);
        String[] subjects = student.getSubjects();
        double[] marks = student.getMarks();

        double total = GradeCalculator.calculateTotal(marks);
        double average = GradeCalculator.calculateAverage(marks);
        double highest = GradeCalculator.findHighestMark(marks);
        double lowest = GradeCalculator.findLowestMark(marks);
        double percentage = GradeCalculator.calculatePercentage(marks);

        String grade = GradeCalculator.calculateGrade(average);
        String status = GradeCalculator.getStatus(average);

        int passedSubjects =GradeCalculator.countPassedSubjects(marks);

        int failedSubjects =GradeCalculator.countFailedSubjects(marks);

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                 STUDENT PERFORMANCE REPORT");
        System.out.println("==============================================================");

        System.out.println("Student ID   : " + student.getStudentId());
        System.out.println("Student Name : " + student.getStudentName());

        System.out.println("--------------------------------------------------------------");

        System.out.printf(
                "%-25s %-15s %-15s%n",
                "Subject",
                "Marks",
                "Grade"
        );

        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < subjects.length; i++) {
            String subjectGrade =GradeCalculator.calculateGrade(marks[i]);
            System.out.printf(
                    "%-25s %-15.2f %-15s%n",
                    subjects[i],
                    marks[i],
                    subjectGrade
            );
        }

        System.out.println("--------------------------------------------------------------");

        System.out.printf("Total Marks       : %.2f%n", total);
        System.out.printf("Average Marks     : %.2f%n", average);
        System.out.printf("Percentage        : %.2f%%%n", percentage);
        System.out.printf("Highest Mark      : %.2f%n", highest);
        System.out.printf("Lowest Mark       : %.2f%n", lowest);
        System.out.println("Overall Grade     : " + grade);
        System.out.println("Status            : " + status);
        System.out.println("Passed Subjects   : " + passedSubjects);
        System.out.println("Failed Subjects   : " + failedSubjects);

        System.out.println("==============================================================");
    }

    // =========================================================
    // CLASS PERFORMANCE SUMMARY
    // =========================================================

    /**
     * Generates complete class performance summary.
     *
     * @param students list of students
     */
    public static void generateClassSummary(List<StudentGrade> students) {
        validateStudentList(students);

        int totalStudents = students.size();

        int passedStudents = 0;
        int failedStudents = 0;

        double totalAverage = 0;

        double highestAverage = Double.MIN_VALUE;
        double lowestAverage = Double.MAX_VALUE;

        StudentGrade topPerformer = null;
        StudentGrade lowestPerformer = null;

        int gradeA = 0;
        int gradeB = 0;
        int gradeC = 0;
        int gradeD = 0;
        int gradeF = 0;

        for (StudentGrade student : students) {
            double average =GradeCalculator.calculateAverage(student.getMarks());
            totalAverage += average;
            // Pass / Fail
            if (GradeCalculator.isPassed(average)) {
                passedStudents++;
            } else {
                failedStudents++;
            }
            // Highest performer
            if (average > highestAverage) {

                highestAverage = average;
                topPerformer = student;
            }

            // Lowest performer
            if (average < lowestAverage) {
                lowestAverage = average;
                lowestPerformer = student;
            }

            // Grade distribution
            String grade =GradeCalculator.calculateGrade(average);

            switch (grade) {
                case "A" -> gradeA++;
                case "B" -> gradeB++;
                case "C" -> gradeC++;
                case "D" -> gradeD++;
                case "F" -> gradeF++;
            }
        }

        double classAverage =totalAverage / totalStudents;

        double passPercentage =(passedStudents * 100.0) / totalStudents;

        double failPercentage =(failedStudents * 100.0) / totalStudents;

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                 CLASS PERFORMANCE SUMMARY");
        System.out.println("==============================================================");

        System.out.println("Total Students       : " + totalStudents);
        System.out.printf("Class Average        : %.2f%n",classAverage);
        System.out.println("Passed Students      : " + passedStudents);
        System.out.printf("Pass Percentage      : %.2f%%%n", passPercentage);
        System.out.println("Failed Students      : " + failedStudents);
        System.out.printf("Fail Percentage      : %.2f%%%n",failPercentage);

        System.out.println("--------------------------------------------------------------");

        System.out.println("GRADE DISTRIBUTION");
        System.out.println("--------------------------------------------------------------");

        System.out.println("A Grade              : " + gradeA);
        System.out.println("B Grade              : " + gradeB);
        System.out.println("C Grade              : " + gradeC);
        System.out.println("D Grade              : " + gradeD);
        System.out.println("F Grade              : " + gradeF);

        System.out.println("--------------------------------------------------------------");

        if (topPerformer != null) {
            double average =GradeCalculator.calculateAverage(topPerformer.getMarks());
           
            System.out.println("TOP PERFORMER");
            System.out.println("Student ID           : "+ topPerformer.getStudentId());
            System.out.println("Student Name         : "+ topPerformer.getStudentName());
            System.out.printf("Average              : %.2f%n", average);
        }

        System.out.println("--------------------------------------------------------------");

        if (lowestPerformer != null) {
            double average =GradeCalculator.calculateAverage(lowestPerformer.getMarks());

            System.out.println("LOWEST PERFORMER");
            System.out.println("Student ID           : "+ lowestPerformer.getStudentId());
            System.out.println("Student Name         : "+ lowestPerformer.getStudentName());
            System.out.printf("Average              : %.2f%n",average);
        }

        System.out.println("==============================================================");
    }

    // =========================================================
    // SORT STUDENTS BY AVERAGE
    // =========================================================

    /**
     * Returns students sorted from highest average
     * to lowest average.
     *
     * Original list is not modified.
     *
     * @param students student list
     * @return sorted student list
     */
    public static List<StudentGrade> getStudentsRankedByAverage(
            List<StudentGrade> students) {

        validateStudentList(students);

        List<StudentGrade> rankedStudents =new ArrayList<>(students);
        rankedStudents.sort(Comparator.comparingDouble((StudentGrade student) ->
                                GradeCalculator.calculateAverage(student.getMarks())).reversed());
        return rankedStudents;
    }

    // =========================================================
    // DISPLAY STUDENT RANKING
    // =========================================================

    /**
     * Displays students ranked by their average marks.
     *
     * @param students student list
     */
    public static void displayStudentRanking(List<StudentGrade> students) {
        validateStudentList(students);
        List<StudentGrade> rankedStudents =getStudentsRankedByAverage(students);
        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                    STUDENT RANKING");
        System.out.println("==============================================================");
        System.out.printf(
                "%-8s %-15s %-20s %-12s %-8s%n",
                "Rank",
                "Student ID",
                "Name",
                "Average",
                "Grade"
        );

        System.out.println("--------------------------------------------------------------");

        int rank = 1;

        for (StudentGrade student : rankedStudents) {

            double average =GradeCalculator.calculateAverage(student.getMarks());

            String grade =GradeCalculator.calculateGrade(average);
            System.out.printf(
                    "%-8d %-15s %-20s %-12.2f %-8s%n",
                    rank,
                    student.getStudentId(),
                    student.getStudentName(),
                    average,
                    grade
            );

            rank++;
        }

        System.out.println("==============================================================");
    }

    // =========================================================
    // SUBJECT-WISE SUMMARY
    // =========================================================

    /**
     * Generates subject-wise performance statistics.
     *
     * This assumes the same subject positions are used
     * consistently across students.
     *
     * @param students student list
     */
    public static void generateSubjectSummary(List<StudentGrade> students) {
        validateStudentList(students);

        StudentGrade firstStudent = students.get(0);

        String[] subjects = firstStudent.getSubjects();

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                  SUBJECT PERFORMANCE");
        System.out.println("==============================================================");

        for (int subjectIndex = 0;subjectIndex < subjects.length; subjectIndex++) {
            double total = 0;
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;
            int count = 0;

            for (StudentGrade student : students) {

                String[] studentSubjects =student.getSubjects();
                double[] marks =student.getMarks();

                if (subjectIndex >= marks.length) {
                    continue;
                }

                total += marks[subjectIndex];

                highest = Math.max(highest,marks[subjectIndex]);

                lowest = Math.min(lowest,marks[subjectIndex]);
                count++;
            }

            if (count == 0) {
                continue;
            }

            double average = total / count;

            System.out.println();
            System.out.println("Subject : " + subjects[subjectIndex]);
            System.out.printf("Average : %.2f%n",average);
            System.out.printf("Highest : %.2f%n",highest);
            System.out.printf( "Lowest  : %.2f%n",lowest );
        }

        System.out.println();
        System.out.println("==============================================================");
    }

    // =========================================================
    // REPORT TEXT
    // =========================================================

    /**
     * Creates a simple text representation of a student's report.
     *
     * Useful if you later want to save reports to a file.
     *
     * @param student student object
     * @return report text
     */
    public static String createStudentReportText(
            StudentGrade student) {

        validateStudent(student);

        StringBuilder report = new StringBuilder();

        double[] marks = student.getMarks();

        double total =
                GradeCalculator.calculateTotal(marks);

        double average =
                GradeCalculator.calculateAverage(marks);

        String grade =
                GradeCalculator.calculateGrade(average);

        String status =
                GradeCalculator.getStatus(average);

        report.append("============================================\n");
        report.append("       STUDENT PERFORMANCE REPORT\n");
        report.append("============================================\n");

        report.append("Student ID   : ")
                .append(student.getStudentId())
                .append("\n");

        report.append("Student Name : ")
                .append(student.getStudentName())
                .append("\n");

        report.append("--------------------------------------------\n");

        String[] subjects = student.getSubjects();

        for (int i = 0; i < subjects.length; i++) {

            report.append(String.format(
                    "%-20s : %.2f%n",
                    subjects[i],
                    marks[i]
            ));
        }

        report.append("--------------------------------------------\n");

        report.append(String.format(
                "Total        : %.2f%n",
                total
        ));

        report.append(String.format(
                "Average      : %.2f%n",
                average
        ));

        report.append("Grade        : ")
                .append(grade)
                .append("\n");

        report.append("Status       : ")
                .append(status)
                .append("\n");

        report.append("============================================\n");

        return report.toString();
    }

    // =========================================================
    // VALIDATION
    // =========================================================

    private static void validateStudent(StudentGrade student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
    }

    private static void validateStudentList(List<StudentGrade> students) {

        if (students == null || students.isEmpty()) {

            throw new IllegalArgumentException("Student list cannot be null or empty.");
        }

        for (StudentGrade student : students) {
            validateStudent(student);
        }
    }
}
