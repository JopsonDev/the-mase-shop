package com.pluralsight.order;

import com.pluralsight.enums.Bread;
import com.pluralsight.enums.MenuAction;
import com.pluralsight.enums.Size;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.toppings.*;
import com.pluralsight.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SandwichBuilder {

    public MenuAction addSandwich(Order o, UserInterface ui) {
        MenuAction action = MenuAction.CONTINUE;

        while(action == MenuAction.CONTINUE) {
            List<Topping> allTops = new ArrayList<>();
            Size size = ui.addSize();

            if (size == null){
                return MenuAction.EXIT;
            }
            Bread bread = ui.addBread();
            if(bread == null){
                return MenuAction.EXIT;
            }
            boolean isToasted = ui.isToasted();
            if(ui.addMeat(allTops,size).equals(MenuAction.EXIT)){
                return MenuAction.EXIT;
            }
            if(ui.addCheese(allTops, size).equals(MenuAction.EXIT)){
                return MenuAction.EXIT;
            }
            if(ui.addToppings(allTops, size).equals(MenuAction.EXIT)){
                return MenuAction.EXIT;
            }
            Sandwich wich = new Sandwich(bread, isToasted, size, allTops);
            System.out.println(wich);
            o.addItems(wich);
            action = MenuAction.BREAK;
        }
        return action;
    }

    public Size determinSize(Scanner scanner){
        while(true) {
            int size = caseNumberCheck(scanner);

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
                default -> System.out.println("Invalid Input\nInput: ");
            }
        }
    }

    public Bread determinBread(Scanner scanner){
        while(true) {
            int bread = caseNumberCheck(scanner);

            switch (bread) {
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

    public MenuAction determineMeat(int meat, List<Topping> allTops, Size size,Scanner scanner){
        UserInterface ui = new UserInterface();
        List<String> meats;
        boolean hasExtraMeat;

        if (meat == -1) {
            return MenuAction.BREAK;
        } else if (meat == 0) {
            return MenuAction.EXIT;
        } else {meats = List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
            hasExtraMeat = wantExtra(scanner);
            Meat m = new Meat(meats.get(meat - 1), size, hasExtraMeat);
            allTops.add(m);System.out.println("Added!");
        }

        return MenuAction.CONTINUE;
    }

    public MenuAction determineCheese(int cheese, Size size, List<Topping> allTops, Scanner scanner){
        List<String> cheeses;
        boolean hasExtraCheese;

        if (cheese == -1) {
            return MenuAction.BREAK;
        } else if (cheese == 0) {
            return MenuAction.EXIT;
        } else {
            cheeses = List.of("American", "Provolone", "Cheddar", "Swiss");
            hasExtraCheese = wantExtra(scanner);
            Cheese c = new Cheese(cheeses.get(cheese - 1), size, hasExtraCheese);
            allTops.add(c);
        }

        return MenuAction.CONTINUE;
    }

    public MenuAction determineRegularToppings(int regularToppings, Size size, List<Topping> allTops){

        if (regularToppings == -1) {
            return MenuAction.BREAK;
        } else if (regularToppings == 0) {
            return MenuAction.EXIT;
        } else {
            List<String> rToppings = List.of("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms");
            RegularTopping rt = new RegularTopping(rToppings.get(regularToppings - 1), size);
            allTops.add(rt);
        }

        return MenuAction.CONTINUE;
    }

    public MenuAction determineSauces(int sauces, Size size, List<Topping> allTops){

        if (sauces == -1) {
            return MenuAction.BREAK;
        } else if (sauces == 0) {
            return MenuAction.EXIT;
        } else {
            List<String> listOfSauces = List.of("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette");
            Sauce s = new Sauce(listOfSauces.get(sauces - 1), size);
            allTops.add(s);
        }

        return MenuAction.CONTINUE;
    }

    public MenuAction determineSides(int sides, Size size, List<Topping> allTops){
        if (sides == -1) {
            return MenuAction.BREAK;
        } else if (sides == 0) {
            return MenuAction.EXIT;
        } else {
            List<String> listOfSides = List.of("Au Jus", "Sauce");
            Side si = new Side(listOfSides.get(sides - 1), size);
            allTops.add(si);
        }

        return MenuAction.CONTINUE;
    }

    public boolean wantExtra(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Would you like extra?(Y/N)");
        String input = scanner.nextLine();
        return (input.equalsIgnoreCase("Y"));
    }

    public int checkNumbers(int maxRange,Scanner scanner) {
        int input = -2;
        while (input < -1) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
            } else {
                input = scanner.nextInt();
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

    public int caseNumberCheck(Scanner scanner){
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
