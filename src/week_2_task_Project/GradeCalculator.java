package week_2_task_Project;


/**
 * GradeCalculator
 *
 * Utility class responsible for calculating:
 * - Total marks
 * - Average marks
 * - Percentage
 * - Highest marks
 * - Lowest marks
 * - Grade
 * - Pass/Fail status
 */
public final class GradeCalculator {

    // Passing marks percentage
    private static final double PASSING_MARKS = 40.0;

    // Private constructor prevents object creation
    private GradeCalculator() {
        throw new UnsupportedOperationException(
                "GradeCalculator is a utility class and cannot be instantiated."
        );
    }

    // =========================================================
    // TOTAL MARKS
    // =========================================================

    /**
     * Calculates the total marks.
     *
     * @param marks array of marks
     * @return total marks
     */
    public static double calculateTotal(double[] marks) {
        validateMarksArray(marks);
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    // =========================================================
    // AVERAGE
    // =========================================================

    /**
     * Calculates the average marks.
     *
     * @param marks array of marks
     * @return average marks
     */
    public static double calculateAverage(double[] marks) {
        validateMarksArray(marks);
        return calculateTotal(marks) / marks.length;
    }

    // =========================================================
    // PERCENTAGE
    // =========================================================

    /**
     * Calculates percentage assuming every subject
     * has a maximum of 100 marks.
     *
     * @param marks array of marks
     * @return percentage
     */
    public static double calculatePercentage(double[] marks) {
        validateMarksArray(marks);
        double total = calculateTotal(marks);
        double maximumMarks = marks.length * 100.0;
        return (total / maximumMarks) * 100;
    }

    // =========================================================
    // HIGHEST MARK
    // =========================================================

    /**
     * Finds the highest mark.
     *
     * @param marks array of marks
     * @return highest mark
     */
    public static double findHighestMark(double[] marks) {
        validateMarksArray(marks);
        double highest = marks[0];
        for (double mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }

        return highest;
    }

    // =========================================================
    // LOWEST MARK
    // =========================================================

    /**
     * Finds the lowest mark.
     *
     * @param marks array of marks
     * @return lowest mark
     */
    public static double findLowestMark(double[] marks) {
        validateMarksArray(marks);
        double lowest = marks[0];
        for (double mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }

    // =========================================================
    // GRADE
    // =========================================================

    /**
     * Calculates grade based on average marks.
     *
     * A = 90 - 100
     * B = 80 - 89
     * C = 70 - 79
     * D = 60 - 69
     * F = Below 60
     *
     * @param average average marks
     * @return grade
     */
    public static String calculateGrade(double average) {
        validateSingleMark(average);
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // =========================================================
    // PASS / FAIL
    // =========================================================

    /**
     * Determines whether the student has passed.
     *
     * @param average average marks
     * @return true if passed, otherwise false
     */
    public static boolean isPassed(double average) {

        validateSingleMark(average);

        return average >= PASSING_MARKS;
    }

    /**
     * Returns PASS or FAIL as text.
     *
     * @param average average marks
     * @return status
     */
    public static String getStatus(double average) {
        return isPassed(average) ? "PASS" : "FAIL";
    }

    // =========================================================
    // SUBJECT-WISE PASS CHECK
    // =========================================================

    /**
     * Checks whether the student passed every subject.
     *
     * @param marks array of marks
     * @return true if all subjects are passed
     */
    public static boolean passedAllSubjects(double[] marks) {

        validateMarksArray(marks);

        for (double mark : marks) {

            if (mark < PASSING_MARKS) {
                return false;
            }
        }

        return true;
    }

    // =========================================================
    // FAILED SUBJECT COUNT
    // =========================================================

    /**
     * Counts the number of failed subjects.
     *
     * @param marks array of marks
     * @return number of failed subjects
     */
    public static int countFailedSubjects(double[] marks) {
        validateMarksArray(marks);
        int failedSubjects = 0;
        for (double mark : marks) {
            if (mark < PASSING_MARKS) {
                failedSubjects++;
            }
        }
        return failedSubjects;
    }

    // =========================================================
    // PASSED SUBJECT COUNT
    // =========================================================

    /**
     * Counts the number of passed subjects.
     *
     * @param marks array of marks
     * @return number of passed subjects
     */
    public static int countPassedSubjects(double[] marks) {
        validateMarksArray(marks);
        int passedSubjects = 0;
        for (double mark : marks) {
            if (mark >= PASSING_MARKS) {
                passedSubjects++;
            }
        }
        return passedSubjects;
    }

    // =========================================================
    // VALIDATION
    // =========================================================

    /**
     * Validates the marks array.
     */
    private static void validateMarksArray(double[] marks) {
        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException("Marks array cannot be null or empty.");
        }
        for (double mark : marks) {
            validateSingleMark(mark);
        }
    }

    /**
     * Validates an individual mark.
     */
    private static void validateSingleMark(double mark) {

        if (Double.isNaN(mark) ||Double.isInfinite(mark) ||mark < 0 ||mark > 100) {
            throw new IllegalArgumentException("Marks must be a valid number between 0 and 100." );
        }
    }

    // =========================================================
    // UTILITY INFORMATION
    // =========================================================

    /**
     * Returns a formatted calculation summary.
     *
     * @param marks array of marks
     * @return formatted summary
     */
    public static String getPerformanceSummary(double[] marks) {

        validateMarksArray(marks);

        double total = calculateTotal(marks);
        double average = calculateAverage(marks);
        double highest = findHighestMark(marks);
        double lowest = findLowestMark(marks);
        String grade = calculateGrade(average);
        String status = getStatus(average);

        return String.format(
                "Total: %.2f | Average: %.2f | Highest: %.2f | " +
                "Lowest: %.2f | Grade: %s | Status: %s",
                total,
                average,
                highest,
                lowest,
                grade,
                status
        );
    }
}
