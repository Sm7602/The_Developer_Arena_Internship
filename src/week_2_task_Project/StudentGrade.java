package week_2_task_Project;

import java.util.Arrays;

/**
 * StudentGrade
 *
 * Represents the academic performance of a student.
 *
 * Stores:
 * - Student ID
 * - Student name
 * - Subject names
 * - Marks obtained in each subject
 */
public class StudentGrade {

    private String studentId;
    private String studentName;
    private String[] subjects;
    private double[] marks;

    /**
     * Parameterized constructor.
     *
     * @param studentId   unique student ID
     * @param studentName student name
     * @param subjects    subject names
     * @param marks       marks obtained in each subject
     */
    public StudentGrade(String studentId,String studentName,String[] subjects,double[] marks) {
        setStudentId(studentId);
        setStudentName(studentName);
        setSubjects(subjects);
        setMarks(marks);
        validateData();
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String[] getSubjects() {
        return Arrays.copyOf(subjects, subjects.length);
    }

    public double[] getMarks() {
        return Arrays.copyOf(marks, marks.length);
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setStudentId(String studentId) {

        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId.trim();
    }

    public void setStudentName(String studentName) {

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.studentName = studentName.trim();
    }

    public void setSubjects(String[] subjects) {

        if (subjects == null || subjects.length == 0) {
            throw new IllegalArgumentException("At least one subject is required.");
        }

        for (String subject : subjects) {

            if (subject == null || subject.trim().isEmpty()) {
                throw new IllegalArgumentException("Subject name cannot be empty.");
            }
        }
        this.subjects = Arrays.copyOf(subjects,subjects.length);
    }

    public void setMarks(double[] marks) {

        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException("At least one mark is required.");
        }

        for (double mark : marks) {

            if (mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
        }
        this.marks = Arrays.copyOf(marks,marks.length);
    }

    // =========================================================
    // VALIDATE DATA
    // =========================================================

    private void validateData() {

        if (subjects.length != marks.length) {

            throw new IllegalArgumentException("Number of subjects must match number of marks.");
        }
    }

    // =========================================================
    // UTILITY METHODS
    // =========================================================

    /**
     * Returns the total number of subjects.
     */
    public int getSubjectCount() {
        return subjects.length;
    }

    /**
     * Returns marks for a particular subject.
     *
     * @param index subject index
     * @return marks obtained
     */
    public double getMark(int index) {

        if (index < 0 || index >= marks.length) {

            throw new IndexOutOfBoundsException("Invalid subject index.");
        }
        return marks[index];
    }

    /**
     * Returns subject name for a particular index.
     *
     * @param index subject index
     * @return subject name
     */
    public String getSubject(int index) {

        if (index < 0 || index >= subjects.length) {

            throw new IndexOutOfBoundsException("Invalid subject index.");
        }
        return subjects[index];
    }

    /**
     * Updates marks for a specific subject.
     *
     * @param index subject index
     * @param mark  new marks
     */
    public void updateMark(int index, double mark) {

        if (index < 0 || index >= marks.length) {
            throw new IndexOutOfBoundsException("Invalid subject index.");
        }

        if (mark < 0 || mark > 100) {

            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }

        marks[index] = mark;
    }

    /**
     * Returns a formatted representation of the student.
     */
    @Override
    public String toString() {

        return "StudentGrade{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subjects=" + Arrays.toString(subjects) +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }
}
