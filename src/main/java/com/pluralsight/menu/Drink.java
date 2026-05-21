package com.pluralsight.menu;

public class Drink implements IChargable{
    private String flavor;
    private String size;

    public Drink(String flavor, String size) {
        this.flavor = flavor;
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        double price = 0;
        switch (size.toLowerCase()){
            case "small" -> price = 2;
            case "medium" -> price = 2.50;
            case "large" -> price = 3;
            default -> price = 0;
        }
        return price;
    }
}
