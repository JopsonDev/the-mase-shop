package com.pluralsight.ui;

import java.util.Scanner;

public class Utilities {

    //gathers users input for switch cases and checks for any errors and returns a value
    public int readIntInput(Scanner scanner){
        int input;
        if(!scanner.hasNextInt()){
            input = -1;
            scanner.nextLine();
        } else {
            input = scanner.nextInt();
            scanner.nextLine();
        }
        return input;
    }

    //takes user input for selecting from list and will loop till an acceptable input is received
    //will return -1 in order to break out of toppings loops
    public int validateMenuChoice(int maxRange, Scanner scanner) {
        int input = -2;
        while (input < -1) {

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
            } else {
                input = scanner.nextInt();
                scanner.nextLine();
            }
            if (input == maxRange) {
                return -1;
            } else if (input == 0) {
                return 0;
            } else if (input > maxRange || input < 0) {
                System.out.println("Invalid input");
                System.out.print("Input: ");
                input = -2;
            }
        }
        return input;
    }
}
