package com.pluralsight.ui;

import com.pluralsight.enums.Bread;
import com.pluralsight.enums.Size;
import com.pluralsight.menu.Chips;
import com.pluralsight.menu.Drink;
import com.pluralsight.menu.IChargable;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.toppings.*;

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
            order.add(sandwich);

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

            int size = scanner.nextInt();
            scanner.nextLine();

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

            int bread = scanner.nextInt();
            scanner.nextLine();

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
        List<String> meats = null;
        int meat;
        boolean hasExtraMeat = false;
        while (true) {
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

            meat = scanner.nextInt();
            scanner.nextLine();
            if (meat == 7) {
                break;
            } else if (meat == 0) {
                return false;
            } else if (meat > 7 || meat < 0){
                System.out.println("Invalid input");
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
        List<String> cheeses = null;
        int cheese;
        boolean hasExtraCheese = false;
        while (true) {
            System.out.println("Premium Toppings");
            System.out.println("Please select your cheese");
            System.out.println("1) -> American");
            System.out.println("2) -> Provolone");
            System.out.println("3) -> Cheddar");
            System.out.println("4) -> Swiss");
            System.out.println("5) -> No more cheese");
            System.out.println("0) -> Never mind I dont want a sandwich");

            cheese = scanner.nextInt();
            scanner.nextLine();
            if (cheese == 5) {
                break;
            } else if (cheese == 0) {
                return false;
            }  else if (cheese > 5 || cheese < 0){
            System.out.println("Invalid input");
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

            int regularToppings = scanner.nextInt();
            scanner.nextLine();
            if (regularToppings == 10) {
                break;
            } else if (regularToppings == 0) {
                return false;
            } else if (regularToppings > 10 || regularToppings < 0){
            System.out.println("Invalid input");
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

            int sauces = scanner.nextInt();
            scanner.nextLine();

            if (sauces == 7) {
                break;
            } else if (sauces == 0) {
                return false;
            } else if (sauces > 7 || sauces < 0){
                System.out.println("Invalid input");
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

            int sides = scanner.nextInt();
            scanner.nextLine();

            if (sides == 3) {
                break;
            } else if (sides == 0) {
                return false;
            } else if (sides > 3 || sides < 0){
                System.out.println("Invalid input");
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
        System.out.println(order.stream().mapToDouble(IChargable::getPrice).sum());
    }

}
