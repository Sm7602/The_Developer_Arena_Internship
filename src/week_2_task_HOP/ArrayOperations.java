package week_2_task_HOP;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create array
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        int[] numbers = new int[size];

        // Input elements
        System.out.println("Enter " + size + " elements:");

        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("\n===== ARRAY OPERATIONS =====");

        // 1. Display array
        System.out.println("Original Array: " + Arrays.toString(numbers));

        // 2. Find sum
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        System.out.println("Sum: " + sum);

        // 3. Find average
        double average = (double) sum / numbers.length;
        System.out.println("Average: " + average);

        // 4. Find maximum
        int max = numbers[0];

        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }

        System.out.println("Maximum: " + max);

        // 5. Find minimum
        int min = numbers[0];

        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }

        System.out.println("Minimum: " + min);

        // 6. Count even and odd numbers
        int even = 0;
        int odd = 0;

        for (int number : numbers) {
            if (number % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        System.out.println("Even numbers: " + even);
        System.out.println("Odd numbers: " + odd);

        // 7. Search for an element
        System.out.print("\nEnter number to search: ");
        int search = scanner.nextInt();

        boolean found = false;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == search) {
                System.out.println("Element found at index: " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Element not found.");
        }

        // 8. Reverse array
        System.out.print("\nReversed Array: ");

        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i] + " ");
        }

        // 9. Sort array
        Arrays.sort(numbers);

        System.out.println("\nSorted Array: " + Arrays.toString(numbers));

        scanner.close();
    }
}
