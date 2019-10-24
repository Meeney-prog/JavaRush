package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int overallDuration = 0;
        for (Dish dish : dishes)
            overallDuration += dish.getDuration();
        return overallDuration;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishes)
            sb.append(dish).append(", ");
        return String.format("Your order: [%s] of %s", sb.toString().substring(0, sb.length() - 2), tablet.toString());
    }
}
