package com.pluralsight.menu;

public class Chips implements IChargable{
    private String flavor;

    public Chips(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }
}
