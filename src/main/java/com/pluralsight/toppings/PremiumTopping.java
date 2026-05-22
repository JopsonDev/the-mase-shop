package com.pluralsight.toppings;

abstract class PremiumTopping extends Topping{
    private boolean hasExtra;

    public PremiumTopping(String name, int size, boolean hasExtra) {
        super(name, size);
        this.hasExtra = hasExtra;
    }

    @Override
    public double getPrice(){
        return 0;
    }

    @Override
    public String toString() {
        return "PremiumTopping{" +
                "hasExtra=" + getName() + hasExtra +
                '}';
    }
}
