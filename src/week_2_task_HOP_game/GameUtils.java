package week_2_task_HOP_game;

import java.util.Scanner;

/**
 * Utility class for the Number Master game.
 *
 * This class contains reusable methods for:
 * - Integer input validation
 * - Range validation
 * - Player name validation
 * - Yes/No input
 * - Non-empty string input
 *
 * This class cannot be instantiated.
 */
public final class GameUtils {

    // Private constructor prevents object creation.
    private GameUtils() {
        throw new IllegalStateException(
                "Utility class cannot be instantiated."
        );
    }

    /**
     * Reads a valid integer from the user.
     *
     * @param scanner Scanner used to read input
     * @param message message displayed before input
     * @return valid integer
     */
    public static int getValidInteger(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(
                        "❌ Input cannot be empty."
                );
                continue;
            }

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException exception) {

                System.out.println(
                        "❌ Invalid input! Please enter a number."
                );
            }
        }
    }

    /**
     * Reads an integer within a specific range.
     *
     * @param scanner Scanner used to read input
     * @param message message displayed before input
     * @param min minimum allowed value
     * @param max maximum allowed value
     * @return valid integer within range
     */
    public static int getValidInteger( Scanner scanner,String message,int min,int max) {

        if (min > max) {
            throw new IllegalArgumentException(
                    "Minimum value cannot be greater than maximum value."
            );
        }

        while (true) {

            int value = getValidInteger(
                    scanner,
                    message
            );

            if (value < min || value > max) {

                System.out.println(
                        "❌ Invalid range!"
                );

                System.out.println(
                        "Please enter a number between "
                                + min
                                + " and "
                                + max
                                + "."
                );

                continue;
            }

            return value;
        }
    }

    /**
     * Reads and validates the player's name.
     *
     * Rules:
     * - Minimum 2 characters
     * - Maximum 20 characters
     * - Cannot be empty
     * - Only letters, numbers, spaces, hyphen and underscore
     *
     * @param scanner Scanner used to read input
     * @return valid player name
     */
    public static String getValidPlayerName(Scanner scanner) {

        while (true) {

            System.out.print("Enter your player name: ");

            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {

                System.out.println(
                        "❌ Player name cannot be empty."
                );

                continue;
            }

            if (name.length() < 2) {

                System.out.println(
                        "❌ Name must contain at least 2 characters."
                );

                continue;
            }

            if (name.length() > 20) {

                System.out.println(
                        "❌ Name cannot contain more than 20 characters."
                );

                continue;
            }

            if (!name.matches("[a-zA-Z0-9 _-]+")) {

                System.out.println(
                        "❌ Invalid characters in name."
                );

                System.out.println(
                        "Allowed characters: A-Z, 0-9, space, _ and -."
                );

                continue;
            }

            return name;
        }
    }

    /**
     * Reads a Yes/No response.
     *
     * Accepted:
     * Y, y, YES, yes
     * N, n, NO, no
     *
     * @param scanner Scanner used to read input
     * @param message message displayed before input
     * @return true for Yes, false for No
     */
    public static boolean getYesNo(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine()
                    .trim()
                    .toLowerCase();

            switch (input) {

                case "y":
                case "yes":
                    return true;

                case "n":
                case "no":
                    return false;

                default:
                    System.out.println(
                            "❌ Invalid input!"
                    );

                    System.out.println(
                            "Please enter Y/Yes or N/No."
                    );
            }
        }
    }

    /**
     * Reads a non-empty string.
     *
     * @param scanner Scanner used to read input
     * @param message message displayed before input
     * @return non-empty string
     */
    public static String getNonEmptyString(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "❌ Input cannot be empty."
            );
        }
    }

    /**
     * Pauses execution until the user presses Enter.
     *
     * @param scanner Scanner used to read input
     */
    public static void pressEnterToContinue(
            Scanner scanner
    ) {

        System.out.println();
        System.out.println(
                "Press ENTER to continue..."
        );

        scanner.nextLine();
    }
}
