package com.pluralsight;

import java.util.Scanner;

public class Console {
    Scanner scanner = new Scanner(System.in);

    public double promptForDouble(String prompt) {
        boolean hasResult = false;
        double result = -1;
        while (!hasResult) {
            try {
                System.out.println(prompt);
                result = scanner.nextDouble();
                scanner.nextLine();
                return result;

            } catch (Exception e) {
                System.out.println("Invalid Entry, try again!");
            }
        }

        return result;
    }

    public String promptforString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}