package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

public class Sauce extends Topping{

    public Sauce(String name, Size size) {
        super(name, size);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
