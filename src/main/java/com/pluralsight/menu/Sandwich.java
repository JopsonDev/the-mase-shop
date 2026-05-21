package com.pluralsight.menu;
import com.pluralsight.toppings.Topping;
import java.util.List;

public class Sandwich implements IChargable{
    private int size;
    private String bread;
    private boolean isTotasted;
    private List<Topping> toppings;

    public Sandwich(String bread, boolean isTotasted, int size, List<Topping> toppings) {
        this.bread = bread;
        this.isTotasted = isTotasted;
        this.size = size;
        this.toppings = toppings;
    }

    public String getBread() {
        return bread;
    }

    public boolean isTotasted() {
        return isTotasted;
    }

    public int getSize() {
        return size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
