package com.pluralsight.menu;

public class Chips implements IChargable{
    private String flavor;

    public Chips(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f",flavor, getPrice());
    }
}
