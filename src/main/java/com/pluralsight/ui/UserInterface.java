package com.pluralsight.ui;

import com.pluralsight.menu.Chips;
import com.pluralsight.menu.Drink;
import com.pluralsight.menu.IChargable;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.toppings.Topping;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private List<IChargable> order = new ArrayList<>();
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
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> displayOrder();
                    case 0 -> {
                        return;
                    }
                }
            }
        }
    }

    public void addSandwich(){
        while(true) {
            System.out.println("Please select your size");
            System.out.println("1) -> 4in  (5.50)");
            System.out.println("2) -> 8in  (7.00)");
            System.out.println("3) -> 12in (8.50)");
            System.out.println("0) -> Nevermind I dont want a sandwich");

            int size = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please select your bread");
            System.out.println("1) -> White");
            System.out.println("2) -> Wheat");
            System.out.println("3) -> Rye");
            System.out.println("4) -> Wrap");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int bread = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Premium Toppings");
            System.out.println("Please select your meat");
            System.out.println("1) -> Steak");
            System.out.println("2) -> Ham");
            System.out.println("3) -> Salami");
            System.out.println("4) -> Roast Beef");
            System.out.println("5) -> Chicken");
            System.out.println("6) -> Bacon");
            System.out.println("7) -> No more meat");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int meat = scanner.nextInt();
            scanner.nextLine();

            //x and y determine the range of meats to choose from
            boolean hasExtraMeat = wantExtra(meat, 7, 0);

            System.out.println("Premium Toppings");
            System.out.println("Please select your cheese");
            System.out.println("1) -> American");
            System.out.println("2) -> Provolone");
            System.out.println("3) -> Cheddar");
            System.out.println("4) -> Swiss");
            System.out.println("5) -> No more cheese");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int cheese = scanner.nextInt();
            scanner.nextLine();

            boolean hasExtraCheese = wantExtra(cheese, 5, 0);


            System.out.println("Regular Toppings");
            System.out.println("Please select your toppings");
            System.out.println(" 1) -> Lettuce");
            System.out.println(" 2) -> Peppers");
            System.out.println(" 3) -> Onions");
            System.out.println(" 4) -> Tomatoes");
            System.out.println(" 5) -> Jalapeños");
            System.out.println(" 6) -> Cucumbers");
            System.out.println(" 7) -> Pickles");
            System.out.println(" 8) -> Guacamole");
            System.out.println(" 9) -> Mushrooms");
            System.out.println("10) -> No more toppings");
            System.out.println(" 0) -> Never mind I dont want a sandwich");

            int regularToppings = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Sauces");
            System.out.println("Please select your sauce");
            System.out.println("1) -> Mayo");
            System.out.println("2) -> Mustard");
            System.out.println("3) -> Ketchup");
            System.out.println("4) -> Ranch");
            System.out.println("5) -> Thousand islands");
            System.out.println("6) -> Vinaigrette");
            System.out.println("7) -> No more sauce");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int sauces = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Sides");
            System.out.println("Please select your side");
            System.out.println("1) -> Au Jus");
            System.out.println("2) -> Sauce");
            System.out.println("3) -> No more sauce");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int sides = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public boolean wantExtra(int topping,int x, int y){
        boolean extra = false;
        if(topping < x && topping > y){
            System.out.println("Would you like extra?(Y/N)");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Y")){
                extra = true;
            }
        }
        return extra;
    }

    public void addDrink() {
        String sodaSize = "";

        System.out.println("What flavor soda would you like? Pepsi, Coke, Crush ect.");
        System.out.print("Input: ");
        String flavor = scanner.nextLine();

        while (sodaSize.isEmpty()) {
            System.out.println("Please select a size");
            System.out.println("1) -> Small");
            System.out.println("2) -> Medium");
            System.out.println("3) -> Large");
            System.out.println("0) -> Nevermind I dont want a drink");

            int size = scanner.nextInt();
            scanner.nextLine();

            sodaSize = "";
            switch (size) {
                case 0 -> {
                    return;
                }
                case 1 -> sodaSize = "Small";
                case 2 -> sodaSize = "Medium";
                case 3 -> sodaSize = "Large";
                default -> System.out.println("invalid input");
            }
        }
        order.add(new Drink(flavor, sodaSize));
    }

    public void addChips(){
        System.out.println("What kind of chips would you like? Lays, Ruffles, Pringles ect.");
        System.out.print("Input: ");
        String flavor = scanner.nextLine();

        order.add(new Chips(flavor));
    }

    public void displayOrder(){
        order.forEach(System.out::println);
    }



}
