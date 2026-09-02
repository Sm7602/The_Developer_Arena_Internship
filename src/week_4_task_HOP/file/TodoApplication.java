package week_4_task_HOP.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApplication {

    private static final String FILE_NAME = "tasks.txt";
    private static final ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Load previously saved tasks
        loadTasks();

        int choice;

        do {
            System.out.println("\n===== TO-DO LIST =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTask(scanner);
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    deleteTask(scanner);
                    break;

                case 4:
                    saveTasks();
                    System.out.println("Tasks saved. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        scanner.close();
    }

    // Add a task
    private static void addTask(Scanner scanner) {

        System.out.print("Enter task: ");
        String task = scanner.nextLine();

        if (task.isBlank()) {
            System.out.println("Task cannot be empty!");
            return;
        }

        tasks.add(task);

        // Save immediately
        saveTasks();

        System.out.println("Task added successfully!");
    }

    // Display all tasks
    private static void viewTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("\n===== YOUR TASKS =====");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // Delete a task
    private static void deleteTask(Scanner scanner) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }

        viewTasks();

        System.out.print("Enter task number to delete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        String removedTask = tasks.remove(taskNumber - 1);

        // Save updated list
        saveTasks();

        System.out.println("Deleted: " + removedTask);
    }

    // Save tasks to file
    private static void saveTasks() {

        try (BufferedWriter writer =new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file
    private static void loadTasks() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =new BufferedReader(new FileReader(FILE_NAME))) {

            String task;

            while ((task = reader.readLine()) != null) {
                if (!task.isBlank()) {
                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
