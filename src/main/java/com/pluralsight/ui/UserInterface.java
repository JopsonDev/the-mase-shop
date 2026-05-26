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
                    case 1 -> addItemsSandwiches();
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

    public void addItemsSandwiches() {
        while(true) {
            MenuAction action = s.addSandwich(order);

            if(action == MenuAction.EXIT){
                return;
            }

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

            return s.determinSize();
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

            return s.determinBread();
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

            int meat = s.checkNumbers(7);
            action = s.determineMeat(meat, allTops, size);
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

            int cheese = s.checkNumbers(5);
            action = s.determinCheese(cheese, size, allTops);
        }
        return action;
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
            int rT = s.checkNumbers(10);
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

            int sauces = s.checkNumbers(7);
            action = s.determinSauces(sauces, size, allTops);
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

            int sides = s.checkNumbers(3);

            action = s.determinSides(sides, size, allTops);
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
