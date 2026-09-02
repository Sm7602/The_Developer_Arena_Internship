package week_4_task_HOP.file;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DiaryApplication {

    private static final String FILE_NAME = "diary.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MY DIARY =====");
            System.out.println("1. Add Diary Entry");
            System.out.println("2. View Diary");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    addEntry(scanner);
                    break;

                case 2:
                    viewDiary();
                    break;

                case 3:
                    System.out.println("Thank you for using My Diary!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);

        scanner.close();
    }

    // Add a new diary entry
    private static void addEntry(Scanner scanner) {

        System.out.print("Write your diary entry: ");
        String entry = scanner.nextLine();

        if (entry.isBlank()) {
            System.out.println("Diary entry cannot be empty!");
            return;
        }

        LocalDate date = LocalDate.now();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write("\n==============================\n");
            writer.write("Date: " + date + "\n");
            writer.write("Entry: " + entry + "\n");
            writer.write("==============================\n");

            System.out.println("Diary entry saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving diary: " + e.getMessage());
        }
    }

    // View all diary entries
    private static void viewDiary() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No diary entries found.");
            return;
        }

        System.out.println("\n===== YOUR DIARY =====");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading diary: " + e.getMessage());
        }
    }
}
