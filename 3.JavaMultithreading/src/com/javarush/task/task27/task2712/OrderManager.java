package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Set<Cook> cooks = StatisticManager.getInstance().getCooks();
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    for (Cook cook : cooks) {
                        if (!cook.isBusy()) {
                            if (!orderQueue.isEmpty()) {
                                cook.startCookingOrder(orderQueue.poll());
                            } else Thread.sleep(10);
                        } else Thread.sleep(10);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        orderQueue.add((Order) arg);
    }
}
