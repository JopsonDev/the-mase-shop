package com.pluralsight.toppings;

import com.pluralsight.menu.IChargable;

abstract class Topping implements IChargable {
    private String name;
    private int size;

    public Topping(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
