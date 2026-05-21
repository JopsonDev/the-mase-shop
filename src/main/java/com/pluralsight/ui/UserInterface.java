package com.pluralsight.ui;

import java.sql.SQLOutput;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    public void display(){
        System.out.println("Welcome!");

        while(true) {
            System.out.println("1) -> Place Order");
            System.out.println("0) -> Quit");

            if(scanner.nextInt() == 0){
                return;
            }
            scanner.nextLine();
            while(true) {
                System.out.println("Home Menu Please Make a Selection");
                System.out.println("1) -> Add Sandwich");
                System.out.println("2) -> Add Drink");
                System.out.println("3) -> Add Chips");
                System.out.println("4) -> Checkout");
                System.out.println("0) -> Quit");
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> System.out.println("A");
                    case 2 -> System.out.println("B");
                    case 3 -> System.out.println("C");
                    case 4 -> System.out.println("D");
                    case 0 -> {
                        return;
                    }
                }
            }
        }
    }
}
