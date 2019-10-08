package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveAdList() {
        List<Advertisement> result = new ArrayList<>();
        storage.list().forEach(ad -> {
            if (ad.getHits() > 0) result.add(ad);
        });
        return result;
    }

    public List<Advertisement> getInactiveAdList() {
        List<Advertisement> result = new ArrayList<>();
        storage.list().forEach(ad -> {
            if (ad.getHits() == 0) result.add(ad);
        });
        return result;
    }
}
