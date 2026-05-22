package com.pluralsight.order;

import com.pluralsight.menu.IChargable;
import com.pluralsight.toppings.Topping;

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

    @Override
    public String toString() {
        return items.stream().map(IChargable::toString).collect(Collectors.joining("\n"));
    }
}
