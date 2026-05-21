package com.pluralsight.toppings;

public class Cheese extends PremiumTopping{

    public Cheese(String name, int size, boolean hasExtra) {
        super(name, size, hasExtra);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
