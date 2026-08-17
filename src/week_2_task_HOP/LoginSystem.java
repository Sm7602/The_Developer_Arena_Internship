package week_2_task_HOP;

import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Stored credentials
        String correctUsername = "admin";
        String correctPassword = "admin123";

        int maxAttempts = 3;
        boolean loggedIn = false;

        System.out.println("===== LOGIN SYSTEM =====");

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (username.equals(correctUsername)
                    && password.equals(correctPassword)) {

                loggedIn = true;
                System.out.println("\nLogin Successful!");
                System.out.println("Welcome, " + username + "!");
                break;

            } else {
                int remainingAttempts = maxAttempts - attempt;

                if (remainingAttempts > 0) {
                    System.out.println("\nInvalid username or password.");
                    System.out.println("Attempts remaining: " + remainingAttempts);
                } else {
                    System.out.println("\nAccount locked!");
                    System.out.println("Too many failed login attempts.");
                }
            }
        }

        if (loggedIn) {
            System.out.println("\n===== DASHBOARD =====");
            System.out.println("1. View Profile");
            System.out.println("2. Logout");
            System.out.println("Thank you for using the system.");
        }

        scanner.close();
    }
}