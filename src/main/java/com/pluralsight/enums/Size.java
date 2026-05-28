package com.pluralsight.enums;

public enum Size {
    SMALL(5.50, "Small"),
    MEDIUM(7.00, "Medium"),
    LARGE(8.50, "Large"),
    NONE(0,"No Size");

    private final double price;
    private final String displayName;

    Size(double price, String displayName) {
        this.price = price;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice(){
        return price;
    }
}
