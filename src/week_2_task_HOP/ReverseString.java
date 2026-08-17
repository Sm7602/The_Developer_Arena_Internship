package week_2_task_HOP;

import java.util.Scanner;

public class ReverseString {
	
	public static String input() {
		Scanner sc=new Scanner(System.in);
		
		String s;
		System.out.println("Enter a String : ");
		s=sc.nextLine();
		sc.close();
		return s;
	}
	
	public static void reverse(String s) {
		 String reversed = "";

	        for (int i = s.length() - 1; i >= 0; i--) {
	            reversed += s.charAt(i);
	        }

	        System.out.println("Original String : " + s);
	        System.out.println("Reversed String : " + reversed);
	}
	
	public static void main(String[] arg) {
		String s=input();
		reverse(s);
	}

}
