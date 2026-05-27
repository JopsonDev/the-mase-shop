package com.pluralsight.ui;

import com.pluralsight.enums.Bread;
import com.pluralsight.enums.MenuAction;
import com.pluralsight.enums.Size;
import com.pluralsight.menu.Chips;
import com.pluralsight.menu.Drink;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.order.Order;
import com.pluralsight.order.SandwichBuilder;
import com.pluralsight.toppings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order = new Order(1);
    private final SandwichBuilder s = new SandwichBuilder();

    public void display(){
        System.out.println("Welcome!");


        System.out.println("1) -> Place Order");
        System.out.println("0) -> Quit");

        if(readIntInput() != 1){
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

                int input = readIntInput();

                switch (input) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> displayOrder();
                    case 0 -> {
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid input");
                }
            }
        }

    public MenuAction addSandwich() {
        MenuAction action = MenuAction.CONTINUE;
        while(action == MenuAction.CONTINUE) {
            List<Topping> allTops = new ArrayList<>();
            Size size = addSize();

            if (size == null){
                return MenuAction.EXIT;
            }
            Bread bread = addBread();
            if(bread == null){
                return MenuAction.EXIT;
            }
            boolean isToasted = isToasted();
            if(addMeat(allTops,size).equals(MenuAction.EXIT)){
                return MenuAction.EXIT;
            }
            if(addCheese(allTops, size).equals(MenuAction.EXIT)){
                return MenuAction.EXIT;
            }
            if(addToppings(allTops, size).equals(MenuAction.EXIT)){
                return MenuAction.EXIT;
            }
            Sandwich wich = new Sandwich(bread, isToasted, size, allTops);
            System.out.println(wich);
            order.addItems(wich);
            action = MenuAction.BREAK;
        }
        System.out.println("Add another?(Y/N)");
        if(scanner.nextLine().equalsIgnoreCase("Y")){
            return MenuAction.CONTINUE;
        }
        return action;
    }

    public Size addSize() {
        while (true) {
            System.out.println("Please select your size");
            System.out.println("1) -> 4in  (5.50)");
            System.out.println("2) -> 8in  (7.00)");
            System.out.println("3) -> 12in (8.50)");

            int choice = readIntInput();
            Size size = s.determineSize(choice);

            if(size != null){
                return size;
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

            int choice = readIntInput();
            Bread bread = s.determineBread(choice);

            if(bread != null){
                return bread;
            }
        }
    }

    public boolean isToasted(){
        System.out.println("Would you like that toasted(Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    public MenuAction addMeat(List<Topping> allTops, Size size) {
        MenuAction action = MenuAction.CONTINUE;
        while (action == MenuAction.CONTINUE) {
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

            boolean extra = false;
            int meat = validateMenuChoice(7);
            if(meat != -1 && meat != 0) {
                extra = wantExtra();
            }
            action = s.determineMeat(meat, allTops, size, extra);
        }
        return action;
    }

    public MenuAction addCheese(List<Topping> allTops, Size size) {
        MenuAction action = MenuAction.CONTINUE;
        while (action == MenuAction.CONTINUE) {
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

            boolean extra = false;
            int cheese = validateMenuChoice(5);
            if(cheese != -1 && cheese != 0) {
                extra = wantExtra();
            }
            action = s.determineCheese(cheese, size, allTops, extra);
        }
        return action;
    }
    public boolean wantExtra() {
        System.out.println("Would you like extra?(Y/N)");
        String input = scanner.nextLine();
        return (input.equalsIgnoreCase("Y"));
    }

    public MenuAction addToppings(List<Topping> allTops, Size size){
        MenuAction action = MenuAction.CONTINUE;
        while (action == MenuAction.CONTINUE) {
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
            int rT = validateMenuChoice(10);
            action = s.determineRegularToppings(rT, size, allTops);
            if (action == MenuAction.EXIT){
                return action;
            }
        }
        action = MenuAction.CONTINUE;
        while (action == MenuAction.CONTINUE) {
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

            int sauces = validateMenuChoice(7);
            action = s.determineSauces(sauces, size, allTops);
            if (action == MenuAction.EXIT){
                return action;
            }
        }
        action = MenuAction.CONTINUE;
        while (action == MenuAction.CONTINUE) {
            System.out.println("Sides");
            System.out.println("Please select your side");
            System.out.println("1) -> Au Jus");
            System.out.println("2) -> Sauce");
            System.out.println("3) -> No more sauce");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int sides = validateMenuChoice(3);

            action = s.determineSides(sides, size, allTops);
            if (action == MenuAction.EXIT){
                return action;
            }
        }
        return action;
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

            int size = readIntInput();

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

    public int readIntInput(){
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

    public int validateMenuChoice(int maxRange) {
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
