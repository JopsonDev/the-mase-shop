package com.pluralsight.toppings;

public class Sauce extends Topping{

    public Sauce(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
