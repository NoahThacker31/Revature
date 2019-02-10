package project0.util;

import java.util.Scanner;

public class InputUtil {
	private static Scanner scanner = new Scanner(System.in);
	
	public static Boolean getBooleanChoice() {
		Boolean inputValue;
		String isYesNo;
		// Asks the user for a yes or a no
		System.out.println("Yes or no?");
		isYesNo = scanner.nextLine();
		
		switch(isYesNo) {
			case "yes": inputValue = true; break;
			case "no": inputValue = false; break;
			default: inputValue = false;
		}
		
		// Returns results
		return inputValue;
	}
	
	public static int getIntChoice(int max) {
		int inputValue;
		// Confirm user input is int type
		do {
			while (!scanner.hasNextInt()) {
				scanner.nextLine();
				System.out.println("Please enter a whole number.");
			}
			// Retrieve user input
			inputValue = scanner.nextInt();
			scanner.nextLine();
			// Confirm user input is within the range of 0 - max	
			if (inputValue < 0 || inputValue > max) {
				System.out.println("Please enter a number between 0 and " + max + ".");
			}
		} while (inputValue < 0 || inputValue > max);
		// Returns the input value
		return inputValue;
	}
	
	public static double getDouble() {
		double inputValue;
		// Confirm user input is int type
		while (!scanner.hasNextDouble()) {
			scanner.nextLine();
			System.out.println("Please enter an amount.");
		}
		// Retrieve user input
		inputValue = scanner.nextDouble();
		scanner.nextLine();
		// Confirm user input is within the range of 0 - max	
		// Returns the input value
		return inputValue;
	}
	
	public static double getInterestNumber() {
		double inputValue;
		do {
			System.out.println("Please enter the interest rate.");
			inputValue = scanner.nextDouble();
			scanner.nextLine();
		}while(inputValue >= 100);
		return inputValue;
	}
	
	public static String getStringInput(int max) {
		String input;
		while(true) {
			input = scanner.nextLine();
			
			input = input.trim();
			if(input.length() == 0) {
				System.out.println("String contained no content. Please enter string again.");
				continue;
			}
			
			if(input.length() > max) {
				System.out.println("String exceeded max length of " + max + ". Please enter string again.");
				continue;
			}
			
			return input;			
		}
	}
}
