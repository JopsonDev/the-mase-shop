package com.pluralsight.toppings;

import com.pluralsight.enums.Size;
import com.pluralsight.menu.IChargable;

public abstract class Topping implements IChargable {
    private String name;
    private Size size;

    public Topping(String name, Size size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}


