package com.pluralsight.toppings;

import com.pluralsight.enums.Size;

abstract class PremiumTopping extends Topping{
    private boolean hasExtra;

    public PremiumTopping(String name, Size size, boolean hasExtra) {
        super(name, size);
        this.hasExtra = hasExtra;
    }

    public boolean isHasExtra() {
        return hasExtra;
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
