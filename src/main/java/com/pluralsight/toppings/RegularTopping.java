package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

public class RegularTopping extends Topping{

    public RegularTopping(String name, Size size) {
        super(name, size);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
