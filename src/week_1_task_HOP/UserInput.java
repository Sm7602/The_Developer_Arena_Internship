package week_1_task_HOP;

import java.util.Scanner;

public class UserInput {
	public static void main(String[] arg) {
		
		Scanner sc = new Scanner(System.in);

        System.out.println("Enter user id: ");
        int id=sc.nextInt();
        
        System.out.print("Id is : "+id);
        
        sc.close();
	}

}
