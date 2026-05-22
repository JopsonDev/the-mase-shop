package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

public class Meat extends PremiumTopping{

    public Meat(String name, Size size, boolean hasExtra) {
        super(name, size, hasExtra);
    }

    @Override
    public double getPrice(){
        double price = 0;
        switch (getSize()){
            case SMALL -> price = 1.00;
            case MEDIUM -> price = 2.00;
            case LARGE -> price = 3.00;
        }
        if(isHasExtra()){
            price *= 2;
        }
        return price;
    }
}
