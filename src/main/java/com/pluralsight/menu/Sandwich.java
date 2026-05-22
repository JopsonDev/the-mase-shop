package com.pluralsight.menu;
import com.pluralsight.enums.Bread;
import com.pluralsight.enums.Size;
import com.pluralsight.toppings.Topping;
import java.util.List;
import java.util.stream.DoubleStream;

public class Sandwich implements IChargable{
    private Size size;
    private Bread bread;
    private boolean isTotasted;
    private List<Topping> toppings;

    public Sandwich(Bread bread, boolean isToasted, Size size, List<Topping> toppings) {
        this.bread = bread;
        this.isTotasted = isToasted;
        this.size = size;
        this.toppings = toppings;
    }

    public Bread getBread() {
        return bread;
    }

    public boolean isTotasted() {
        return isTotasted;
    }

    public Size getSize() {
        return size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        double price = getSize().getPrice();
        price += toppings.stream().mapToDouble(Topping::getPrice).sum();
        return price;
    }


    @Override
    public String toString() {
        return "Sandwich{" +
                "bread ='" + bread + '\'' +
                ", size =" + size +
                ", isTotasted =" + isTotasted +
                ", toppings =" + toppings +
                '}';
    }
}
