package week_4_task_HOP.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveDataToFile {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your city: ");
        String city = scanner.nextLine();

        try {
            FileWriter writer = new FileWriter("user_data.txt");

            writer.write("User Information\n");
            writer.write("-----------------\n");
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("City: " + city + "\n");

            writer.close();

            System.out.println("\nData saved successfully!");
            System.out.println("File: user_data.txt");

        } catch (IOException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }

        scanner.close();
    }
}