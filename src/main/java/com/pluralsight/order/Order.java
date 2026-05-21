package com.pluralsight.order;

import com.pluralsight.menu.IChargable;

import java.util.List;

public class Order {
    private List<IChargable> items;
    private int idNumber;

    public Order(int idNumber, List<IChargable> items) {
        this.idNumber = idNumber;
        this.items = items;
    }

    public double calculatePrice(){
        return 0;
    }

    public void saveReceipt(){
    }

    public void printOrder(){
    }
}
