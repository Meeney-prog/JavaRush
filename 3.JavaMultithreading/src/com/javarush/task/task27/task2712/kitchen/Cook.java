package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable {
    private String name;
    private boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        try {
            StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.toString(), name, order.getTotalCookingTime(), order.getDishes()));
            ConsoleHelper.writeMessage("Start cooking - " + order + ",cooking time " + order.getTotalCookingTime() / 60 + "min");
            setChanged();
            notifyObservers(order);
            Thread.sleep(order.getTotalCookingTime() / 6);
        } catch (InterruptedException e) {
        }
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return name;
    }
}
