package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        Map<String, Double> advertisementProfit = StatisticManager.getInstance().getAdvertisementProfit();
        double total = 0;
        for (Map.Entry<String, Double> entry : advertisementProfit.entrySet())
            if (entry.getValue() > 0) {
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", entry.getKey(), entry.getValue()));
                total += entry.getValue();
            }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total));
    }

    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> cookWorkload = StatisticManager.getInstance().getCookWorkloading();
        for (Map.Entry<String, Map<String, Integer>> outerEntry : cookWorkload.entrySet()) {
            ConsoleHelper.writeMessage(outerEntry.getKey());
            for (Map.Entry<String, Integer> innerEntry : outerEntry.getValue().entrySet())
                ConsoleHelper.writeMessage(innerEntry.getKey() + " - " + innerEntry.getValue() + " min");
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getActiveAdList();
        list.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        for (Advertisement advertisement : list)
            ConsoleHelper.writeMessage(advertisement.getName() + " - " + advertisement.getHits());
    }

    public void printArchivedVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getInactiveAdList();
        list.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        for (Advertisement advertisement : list)
            ConsoleHelper.writeMessage(advertisement.getName());
    }
}