package com.pluralsight;

import java.time.LocalDate;
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
                System.out.println(ColorCodes.RED + "Invalid Entry, try again!" + ColorCodes.RESET);
            }
        }

        return result;
    }

    public String promptForString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}