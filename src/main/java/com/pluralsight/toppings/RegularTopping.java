package com.pluralsight.toppings;

public class RegularTopping extends Topping{

    public RegularTopping(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
