package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    public void initDishes() throws IOException {
        int i = (int) (Math.random() * 10);
        dishes = new ArrayList<>();
        do {
            dishes.add(Dish.values()[(int) (Math.random() * Dish.values().length)]);
        } while (i-- > 0);
    }
}
