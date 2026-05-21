package com.pluralsight.toppings;

public class Meat extends PremiumTopping{

    public Meat(String name, int size, boolean hasExtra) {
        super(name, size, hasExtra);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
