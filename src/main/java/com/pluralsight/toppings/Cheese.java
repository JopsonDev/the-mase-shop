package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

public class Cheese extends PremiumTopping{

    public Cheese(String name, Size size, boolean hasExtra) {
        super(name, size, hasExtra);
    }

    @Override
    public double getPrice(){
        double price = 0;
        switch (getSize()){
            case SMALL -> price = .75;
            case MEDIUM -> price = 1.50;
            case LARGE -> price = 2.25;
        }
        if(isHasExtra()){
            price *= 2;
        }
        return price;
    }
}
