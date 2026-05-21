package com.pluralsight.toppings;

public class Side extends Topping{

    public Side(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
