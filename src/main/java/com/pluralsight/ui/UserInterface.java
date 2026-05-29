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
    private final Header h = new Header();
    private final Utilities u = new Utilities();
    private MenuAction action = MenuAction.CONTINUE;

    //Prints of the start of program and first two menus
    public void display(){
        h.menu();

        System.out.println("Welcome!");


        System.out.println("1) -> Place Order");
        System.out.println("0) -> Quit");

        if(u.readIntInput(scanner) != 1){
            System.out.println("Exiting program: Have a great day!");
            return;
        }
            while(true) {
                h.header();
                System.out.println("Home Menu Please Make a Selection");
                System.out.println("1) -> Add Sandwich");
                System.out.println("2) -> Add Drink");
                System.out.println("3) -> Add Chips");
                System.out.println("4) -> Signature Sandwiches");
                System.out.println("5) -> Checkout");
                System.out.println("0) -> Quit");

                int input = u.readIntInput(scanner);

                switch (input) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> signatureSandwich();
                    case 5 -> checkout();
                    case 0 -> {
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid input");
                }
            }
        }

    //gathers input from user from other methods and builds a sandwich
    public MenuAction addSandwich() {
        h.sandwichHeader();
        while(action == MenuAction.CONTINUE) {
            List<Topping> allTops = new ArrayList<>();
            Size size = addSize("$5.50/$7.00/$8.50");

            if (size == Size.NONE) {
                return MenuAction.EXIT;
            }

            Bread bread = addBread();
            if (bread == Bread.NONE) {
                return MenuAction.EXIT;
            }

            boolean isToasted = isToasted();
            if (addMeat(allTops, size).equals(MenuAction.EXIT)) {
                return MenuAction.EXIT;
            }

            if (addCheese(allTops, size).equals(MenuAction.EXIT)) {
                return MenuAction.EXIT;
            }

            if (addToppings(allTops, size).equals(MenuAction.EXIT)) {
                return MenuAction.EXIT;
            }

            Sandwich wich = new Sandwich(bread, isToasted, size, allTops);
            System.out.println(wich);
            order.addItems(wich);
            action = MenuAction.BREAK;

            System.out.println("Add another?(Y/N)");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                action = MenuAction.CONTINUE;
            }
        }
        return action;
    }

    //ask user what size they want
    public Size addSize(String prices) {
        Size size = null;
        while (size == null) {
            displaySizeMenu(prices);

            int choice = u.readIntInput(scanner);
            size = s.determineSize(choice);

        }
        return size;
    }

    //ask the user what bread they want
    public Bread addBread() {
        Bread bread = null;
        while (bread == null) {
            displayBreadMenu();

            int choice = u.readIntInput(scanner);
            bread = s.determineBread(choice);
        }
        return bread;
    }

    //determines if toasted
    public boolean isToasted(){
        System.out.println("Would you like that toasted(Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    //gathers what meat toppings the user wants
    public MenuAction addMeat(List<Topping> allTops, Size size) {
        resetAction();
        while (action == MenuAction.CONTINUE) {
            displayMeatMenu();

            boolean extra = false;
            int meat = u.validateMenuChoice(7, scanner);
            if(meat != -1 && meat != 0) {
                extra = wantExtra("meat");
            }
            action = s.determineMeat(meat, allTops, size, extra);
        }
        return action;
    }

    //gathers what cheese toppings the user wants
    public MenuAction addCheese(List<Topping> allTops, Size size) {
        resetAction();
        while (action == MenuAction.CONTINUE) {
            displayCheeseMenu();

            boolean extra = false;
            int cheese = u.validateMenuChoice(5, scanner);
            if(cheese != -1 && cheese != 0) {
                extra = wantExtra("cheese");
            }
            action = s.determineCheese(cheese, size, allTops, extra);
        }
        return action;
    }

    //checks to see if the user wants extra of whatever String is entered
    public boolean wantExtra(String topping) {
        System.out.println("Would you like extra " + topping + "?(Y/N)");
        String input = scanner.nextLine();
        return (input.equalsIgnoreCase("Y"));
    }

    //goes through regular toppings, sauces, and sides to see what the user wants
    public MenuAction addToppings(List<Topping> allTops, Size size){
        resetAction();
        while (action == MenuAction.CONTINUE) {

            displayRegularToppings();

            int rT = u.validateMenuChoice(10, scanner);
            action = s.determineRegularToppings(rT, size, allTops);
            if (action == MenuAction.EXIT){
                return action;
            }
        }
        resetAction();
        while (action == MenuAction.CONTINUE) {

            displaySauceMenu();

            int sauces = u.validateMenuChoice(7, scanner);
            action = s.determineSauces(sauces, size, allTops);
            if (action == MenuAction.EXIT){
                return action;
            }
        }
        resetAction();
        while (action == MenuAction.CONTINUE) {

            displaySidesMenu();

            int sides = u.validateMenuChoice(3, scanner);

            action = s.determineSides(sides, size, allTops);
            if (action == MenuAction.EXIT){
                return action;
            }
        }
        return action;
    }

    //sees what drink the user wants
    public void addDrink() {
        h.drinksHeader();
        String sodaSize = "";

        System.out.println("What flavor soda would you like? Pepsi, Coke, Crush ect.");
        System.out.print("Input: ");
        String flavor = scanner.nextLine();

        while (sodaSize.isEmpty()) {
            System.out.println("Please select a size");
            System.out.println("$2.00/$2.50/$3.00");
            System.out.println("1) -> Small");
            System.out.println("2) -> Medium");
            System.out.println("3) -> Large");
            System.out.println("0) -> Nevermind I dont want a drink");

            int size = u.readIntInput(scanner);

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
        order.addItems(new Drink(flavor, sodaSize));
    }

    //sees what chips the use wants
    public void addChips(){
        h.chipsHeader();
        System.out.println("Chips: $1.50");
        System.out.println("What kind of chips would you like? Lays, Ruffles, Pringles ect.");
        System.out.print("Input: ");
        String flavor = scanner.nextLine();

        order.addItems(new Chips(flavor));
    }

    //shows the user a file receipt where they can choose to pay and check out then clears cart
    public void checkout(){
        h.checkoutHeader();
        order.printOrder();

        System.out.println("$" + String.format("%,.2f", order.calculatePrice()));

        System.out.println("Finish and pay?(Y/N)");
        String input = scanner.nextLine();

        if(input.equalsIgnoreCase("Y")){
            order.saveReceipt(order);
            order.clearOrder();
        }
    }

    //gives the user some options of premade orders
    public void signatureSandwich(){
        while(true) {
            h.sandwichHeader();
            System.out.println("Please Select your sandwich");
            System.out.println("1) -> BLT");
            System.out.println("2) -> CheeseSteak");
            System.out.println("3) -> Chicken Wrap");
            System.out.println("0) -> Never mind I dont want a sandwich");

            int input = u.readIntInput(scanner);

            if (input <= 3 && input > 0) {
                Size size = addSize("$7.25/$10.50/$13.75");
                if(size == Size.NONE){
                    return;
                }
                boolean isToasted = isToasted();
                System.out.println("Extra Meat: $1.00/$2.00/$3.00");
                boolean hasExtraMeat = wantExtra("meat");
                System.out.println("Extra Cheese: $0.30/$0.60/$0.90");
                boolean hasExtraCheese = wantExtra("cheese");

                order.addItems(s.signaturePick(input, order, size, isToasted, hasExtraMeat, hasExtraCheese));
                return;
            } else if (input == 0) {
                return;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
    
    //Responsible for displaying all menus
    public void displaySizeMenu(String prices){
        System.out.println("Please select your size");
        System.out.println(prices);
        System.out.println("1) -> 4in");
        System.out.println("2) -> 8in");
        System.out.println("3) -> 12in");
        System.out.println("0) -> Nevermind I dont want a sandwich");
    }

    public void displayBreadMenu(){
        System.out.println("Please select your bread");
        System.out.println("1) -> White");
        System.out.println("2) -> Wheat");
        System.out.println("3) -> Rye");
        System.out.println("4) -> Wrap");
        System.out.println("0) -> Never mind I dont want a sandwich");
    }

    public void displayCheeseMenu(){
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
    }

    public void displayRegularToppings(){
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
    }

    public void displaySauceMenu(){
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
    }

    public void displaySidesMenu(){
        System.out.println("Sides");
        System.out.println("Please select your side");
        System.out.println("1) -> Au Jus");
        System.out.println("2) -> Sauce");
        System.out.println("3) -> No more sauce");
        System.out.println("0) -> Never mind I dont want a sandwich");
    }

    public void displayMeatMenu(){
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
    }

    public void resetAction(){
        action = MenuAction.CONTINUE;
    }

}
