package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        Order order = null;
        try {
            order = new Order(this);
            if (order.isEmpty()){
                return null;
            }
            AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime());
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            notifyObservers(order);
            manager.processVideos();
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
            return null;
        }
    }

     public void createTestOrder(){
         Order order = null;
         try {
             order = new TestOrder(this);
             AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime());
             ConsoleHelper.writeMessage(order.toString());
             setChanged();
             notifyObservers(order);
             manager.processVideos();
         } catch (IOException e) {
             logger.log(Level.SEVERE, "Console is unavailable.");
         } catch (NoVideoAvailableException e) {
             logger.log(Level.INFO, "No video is available for the order " + order);
         }
     }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}