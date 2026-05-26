package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

public class Meat extends PremiumTopping {

    public Meat(String name, Size size, boolean hasExtra) {
        super(name, size, hasExtra);
    }

    @Override
    public double getPrice() {
        double price = 0;
        switch (getSize()) {
            case SMALL -> price = 1.00;
            case MEDIUM -> price = 2.00;
            case LARGE -> price = 3.00;
        }
        if (isHasExtra()) {
            switch (getSize()){
                case SMALL -> price += .50;
                case MEDIUM -> price += 1;
                case LARGE -> price += 1.50;
            }
        }
        return price;
    }
}
