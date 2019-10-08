package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return instance;
    }

    private StatisticManager() {
    }


    public Map<String, Double> getAdvertisementProfit() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Double> result = new TreeMap<>(Comparator.comparing(o -> {
            try {
                return format.parse((String) o);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }).reversed());
        for (Map.Entry<EventType, List<EventDataRow>> entry : statisticStorage.storage.entrySet()) {
            if (entry.getKey().equals(EventType.SELECTED_VIDEOS)) {
                for (EventDataRow data : entry.getValue()) {
                    String date = format.format(data.getDate());
                    VideoSelectedEventDataRow videoData = (VideoSelectedEventDataRow) data;
                    Double amount = videoData.getAmount() * 0.01d;
                    if (!result.containsKey(date)) {
                        result.put(date, amount);
                    } else {
                        result.put(date, result.get(date) + amount);
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Map<String, Integer>> getCookWorkloading() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Map<String, Integer>> result = new TreeMap<>(Comparator.comparing(o -> {
            try {
                return format.parse((String) o);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }).reversed());
        for (Map.Entry<EventType, List<EventDataRow>> entry : statisticStorage.storage.entrySet()) {
            if (entry.getKey().equals(EventType.COOKED_ORDER)) {
                for (EventDataRow data : entry.getValue()) {
                    String date = format.format(data.getDate());
                    String cookName = ((CookedOrderEventDataRow) data).getCookName();
                    Integer workload = (int) Math.round((double) data.getTime() / 60.0);
                    if (!result.containsKey(date)) {
                        Map<String, Integer> map = new TreeMap<>();
                        map.put(cookName, workload);
                        result.put(date, map);
                    } else {
                        if (!result.get(date).containsKey(cookName)) {
                            result.get(date).put(cookName, workload);
                        } else {
                            result.get(date).put(cookName, result.get(date).get(cookName) + workload);
                        }
                    }
                }
            }
        }
        return result;
    }

    public void register(Cook cook) {
        if (cook != null)
            cooks.add(cook);
    }

    public void register(EventDataRow data) {
        getInstance().statisticStorage.storage.get(data.getType()).add(data);
    }

    public Set<Cook> getCooks() {
        return cooks;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            if (data != null) {
                storage.get(data.getType()).add(data);
            }
        }


        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }
}
