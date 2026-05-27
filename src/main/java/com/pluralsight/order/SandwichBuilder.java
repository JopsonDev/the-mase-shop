package com.pluralsight.order;

import com.pluralsight.enums.Bread;
import com.pluralsight.enums.MenuAction;
import com.pluralsight.enums.Size;
import com.pluralsight.toppings.*;

import java.util.List;

public class SandwichBuilder {

    public Size determineSize(int size){
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
            default -> {
                System.out.println("Invalid Input");;
            }
        }
        return null;
    }

    public Bread determineBread(int bread){
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
            default -> System.out.println("Invalid Input");
        }
        return null;
    }


    public MenuAction determineMeat(int meat, List<Topping> allTops, Size size, boolean hasExtraMeat){
        List<String> meats;

        if (meat == -1) {
            return MenuAction.BREAK;
        } else if (meat == 0) {
            return MenuAction.EXIT;
        } else {meats = List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
            Meat m = new Meat(meats.get(meat - 1), size, hasExtraMeat);
            allTops.add(m);System.out.println("Added!");
        }

        return MenuAction.CONTINUE;
    }

    public MenuAction determineCheese(int cheese, Size size, List<Topping> allTops, boolean hasExtraCheese ){
        List<String> cheeses;


        if (cheese == -1) {
            return MenuAction.BREAK;
        } else if (cheese == 0) {
            return MenuAction.EXIT;
        } else {
            cheeses = List.of("American", "Provolone", "Cheddar", "Swiss");
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
}
