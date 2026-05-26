package com.pluralsight.ui;

import com.pluralsight.enums.Bread;
import com.pluralsight.enums.Size;
import com.pluralsight.menu.Chips;
import com.pluralsight.menu.Drink;
import com.pluralsight.menu.IChargable;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.order.Order;
import com.pluralsight.order.ReceiptHandler;
import com.pluralsight.toppings.*;

import java.awt.image.SinglePixelPackedSampleModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order order = new Order(1);


    public void display(){
        System.out.println("Welcome!");


        System.out.println("1) -> Place Order");
        System.out.println("0) -> Quit");

        if(caseNumberCheck() != 1){
            System.out.println("Exiting program: Have a great day!");
            return;
        }
            while(true) {
                System.out.println("Home Menu Please Make a Selection");
                System.out.println("1) -> Add Sandwich");
                System.out.println("2) -> Add Drink");
                System.out.println("3) -> Add Chips");
                System.out.println("4) -> Checkout");
                System.out.println("0) -> Quit");

                int input = caseNumberCheck();

                switch (input) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> displayOrder();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid input");
                }
            }
        }

    public void addSandwich() {
        while(true) {
            List<Topping> allTops = new ArrayList<>();
            Size size = addSize();
            if (size == null){
                return;
            }
            Bread bread = addBread();
            if(bread == null){
                return;
            }
            boolean isToasted = isToasted();
            if(!addMeat(size, allTops)){
                return;
            }
            if(!addCheese(size, allTops)){
                return;
            }
            if(!addToppings(size, allTops)){
                return;
            }
            Sandwich sandwich = new Sandwich(bread, isToasted, size, allTops);
            System.out.println(sandwich);
            order.addItems(sandwich);

            System.out.println("Add another?(Y/N)");
            if(scanner.nextLine().equalsIgnoreCase("N")){
                return;
            }
        }
    }

    public Size addSize() {
        while (true) {
            System.out.println("Please select your size");
            System.out.println("1) -> 4in  (5.50)");
            System.out.println("2) -> 8in  (7.00)");
            System.out.println("3) -> 12in (8.50)");
            System.out.println("0) -> Nevermind I dont want a sandwich");

            int size = caseNumberCheck();

            switch (size) {
                case 1 -> {
                    return Size.SMALL;
                }
                case 2 -> {
                    return Size.MEDIUM;
                }
                case 3 -> {
                    return Size.LARGE;
                }
                case 0 -> {
                    return null;
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }

    public Bread addBread() {
        while (true) {
            System.out.println("Please select your bread");
            System.out.println("1) -> White");
            System.out.println("2) -> Wheat");
            System.out.println("3) -> Rye");
            System.out.println("4) -> Wrap");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int bread = caseNumberCheck();

            switch(bread){
                case 1 -> {
                    return Bread.WHITE;
                }
                case 2 -> {
                    return Bread.WHEAT;
                }
                case 3 -> {
                    return Bread.RYE;
                }
                case 4 -> {
                    return Bread.WRAP;
                }
                case 0 -> {
                    return null;
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }

    public boolean isToasted(){
        System.out.println("Would you like that toasted(Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    public boolean addMeat(Size size, List<Topping> allTops) {
        List<String> meats;
        int meat;
        boolean hasExtraMeat;
        while (true) {
            System.out.println("Premium Toppings");
            System.out.println("Meats: $1.00/$2.00/$3.00");
            System.out.println("Extra: $.50/$1.00/$1.50");
            System.out.println("Please select your meat");
            System.out.println("1) -> Steak");
            System.out.println("2) -> Ham");
            System.out.println("3) -> Salami");
            System.out.println("4) -> Roast Beef");
            System.out.println("5) -> Chicken");
            System.out.println("6) -> Bacon");
            System.out.println("7) -> No more meat");
            System.out.println("0) -> Never mind I dont want a sandwich");

            meat = checkNumbers(7);
            if (meat == 1) {
                break;
            } else if (meat == 0) {
                return false;
            } else {
                meats = List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
                hasExtraMeat = wantExtra();
                Meat m = new Meat(meats.get(meat - 1), size, hasExtraMeat);
                allTops.add(m);
            }
        }
        return true;
    }

    public boolean addCheese(Size size, List<Topping> allTops) {
        List<String> cheeses;
        int cheese;
        boolean hasExtraCheese;
        while (true) {
            System.out.println("Premium Toppings");
            System.out.println("Cheese: $.75/$1.50/$2.25");
            System.out.println("Extra: $.30/$.60/$.90");
            System.out.println("Please select your cheese");
            System.out.println("1) -> American");
            System.out.println("2) -> Provolone");
            System.out.println("3) -> Cheddar");
            System.out.println("4) -> Swiss");
            System.out.println("5) -> No more cheese");
            System.out.println("0) -> Never mind I dont want a sandwich");

            cheese = checkNumbers(5);
            if (cheese == 1) {
                break;
            } else if (cheese == 0) {
                return false;
            } else {
                cheeses = List.of("American", "Provolone", "Cheddar", "Swiss");
                hasExtraCheese = wantExtra();
                Cheese c = new Cheese(cheeses.get(cheese - 1), size, hasExtraCheese);
                allTops.add(c);
            }
        }
        return true;
    }

    public boolean addToppings(Size size, List<Topping> allTops){
        while(true) {
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

            int regularToppings = checkNumbers(10);
            if (regularToppings == 1) {
                break;
            } else if (regularToppings == 0) {
                return false;
            } else {
                List<String> rToppings = List.of("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms");
                RegularTopping rt = new RegularTopping(rToppings.get(regularToppings - 1), size);
                allTops.add(rt);
            }
        }
        while(true) {
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

            int sauces = checkNumbers(7);
            if (sauces == 1) {
                break;
            } else if (sauces == 0) {
                return false;
            } else {
                List<String> listOfSauces = List.of("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette");
                Sauce s = new Sauce(listOfSauces.get(sauces - 1), size);
                allTops.add(s);
            }
        }
        while(true) {
            System.out.println("Sides");
            System.out.println("Please select your side");
            System.out.println("1) -> Au Jus");
            System.out.println("2) -> Sauce");
            System.out.println("3) -> No more sauce");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int sides = checkNumbers(7);
            if (sides == 1) {
                break;
            } else if (sides == 0) {
                return false;
            } else {
                List<String> listOfSides = List.of("Au Jus", "Sauce");
                Side si = new Side(listOfSides.get(sides - 1), size);
                allTops.add(si);
            }
        }
        return true;
    }

    public boolean wantExtra() {
        System.out.println("Would you like extra?(Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
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

            int size = caseNumberCheck();

            sodaSize = "";
            switch (size) {
                case 0 -> {
                    return;
                }
                case 1 -> sodaSize = "Small ($2.00) ";
                case 2 -> sodaSize = "Medium ($2.50)";
                case 3 -> sodaSize = "Large ($3.00)";
                default -> System.out.println("invalid input");
            }
        }
        order.addItems(new Drink(flavor, sodaSize));
    }

    public void addChips(){
        System.out.println("Chips: $1.50");
        System.out.println("What kind of chips would you like? Lays, Ruffles, Pringles ect.");
        System.out.print("Input: ");
        String flavor = scanner.nextLine();

        order.addItems(new Chips(flavor));
    }

    public void displayOrder(){
        order.printOrder();
        System.out.println("$" + String.format("%,.2f", order.calculatePrice()));
        System.out.println("Finish and pay?(Y/N)");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("Y")){
            order.saveReceipt();
            order.clearOrder();
        }
    }

    public int checkNumbers(int maxRange) {
        int input = -1;
        while (input < 0) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
            } else {
                input = scanner.nextInt();
                scanner.nextLine();
            }
            if (input == maxRange) {
                return 1;
            } else if (input == 0) {
                return 0;
            } else if (input > maxRange || input < 0) {
                System.out.println("Invalid input");
                System.out.print("Input: ");
            }
        }
        return input;
    }

    public int caseNumberCheck(){
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
}
