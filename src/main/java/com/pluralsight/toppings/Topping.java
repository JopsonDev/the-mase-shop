package com.pluralsight.toppings;

import com.pluralsight.menu.IChargable;

public abstract class Topping implements IChargable {
    private String name;
    private int size;

    public Topping(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String toString() {
        return "Topping{" +
                "name='" + name + '\'' +
                '}';
    }
}


