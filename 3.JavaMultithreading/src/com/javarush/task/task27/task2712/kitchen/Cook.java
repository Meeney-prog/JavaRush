package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Restaurant;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();


    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        try {
            StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
            ConsoleHelper.writeMessage("Start cooking - " + order + ",cooking time " + order.getTotalCookingTime() + "min");
            setChanged();
            notifyObservers(order);
            Thread.sleep(order.getTotalCookingTime() * 10);
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

    public void setQueue(LinkedBlockingQueue<Order> orderQueue) {
        this.queue = orderQueue;
    }

    @Override
    public void run() {
        try {
            while (true)
                if (!this.isBusy())
                    if (!queue.isEmpty())
                        this.startCookingOrder(queue.poll());
                    else Thread.sleep(10);
                else Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
