package com.pluralsight.enums;

public enum Size {
    SMALL(5.50),
    MEDIUM(7.00),
    LARGE(8.50);

    private final double price;

    Size(double price) {
        this.price = price;
    }

    public double getPrice(Size size){
        return size.price;
    }
}
