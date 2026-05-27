package com.pluralsight.order;

import com.pluralsight.enums.Bread;
import com.pluralsight.enums.Size;
import com.pluralsight.menu.IChargable;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.toppings.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<IChargable> items;
    private int idNumber;

    public Order(int idNumber) {
        this.idNumber = idNumber;
        this.items = new ArrayList<>();
    }

    public void addItems(IChargable item){
        items.add(item);
    }

    public double calculatePrice(){
        double price = 0;
        price += items.stream().mapToDouble(IChargable::getPrice).sum();
        return price;
    }

    public void clearOrder(){
        items.clear();
    }

    public void saveReceipt(){
        ReceiptHandler r = new ReceiptHandler();
        r.fileReceipt(items, idNumber);
    }

    public void printOrder(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dateTime = LocalDateTime.now().format(formatter);

        System.out.println("****RECEIPT****");
        System.out.println("Order ID: " + idNumber);
        System.out.println(dateTime);
        System.out.println(items.stream().map(IChargable::toString).collect(Collectors.joining("\n")));
    }

    public Sandwich bltOrder(Size size, boolean toasted, boolean extraMeat, boolean extraCheese){
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new RegularTopping("Tomato", size));
        toppings.add(new Meat("Bacon", size, extraMeat));
        toppings.add(new Cheese("American", size, extraCheese));
        toppings.add(new RegularTopping("Lettuce", size));
        toppings.add(new Sauce("Mayo", size));

        return new Sandwich(Bread.WHITE, toasted, size, toppings);
    }

    public Sandwich cheeseSteak(Size size, boolean toasted, boolean extraMeat, boolean extraCheese){
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new Meat("Steak", size, extraMeat));
        toppings.add(new Cheese("American", size, extraCheese));
        toppings.add(new RegularTopping("Peppers", size));
        toppings.add(new RegularTopping("Onions", size));

        return new Sandwich(Bread.WHITE, toasted, size, toppings);
    }

    public Sandwich chickenWrap(Size size, boolean toasted, boolean extraMeat, boolean extraCheese){
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new Meat("Chicken", size, extraMeat));
        toppings.add(new Cheese("Provolone", size, extraCheese));
        toppings.add(new RegularTopping("Lettuce", size));
        toppings.add(new RegularTopping("Tomato", size));

        return new Sandwich(Bread.WRAP, toasted, size, toppings);
    }

    @Override
    public String toString() {
        return items.stream().map(IChargable::toString).collect(Collectors.joining("\n"));
    }
}
