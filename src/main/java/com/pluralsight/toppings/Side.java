package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

public class Side extends Topping{

    public Side(String name, Size size) {
        super(name, size);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
