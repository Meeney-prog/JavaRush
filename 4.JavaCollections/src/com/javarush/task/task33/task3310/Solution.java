package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new OurHashMapStorageStrategy(), 10_000);
        testStrategy(new HashMapStorageStrategy(), 10_000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10_000);
        testStrategy(new HashBiMapStorageStrategy(), 10_000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10_000);
        testStrategy(new FileStorageStrategy(), 100);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String s : strings)
            result.add(shortener.getId(s));
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long num : keys)
            result.add(shortener.getString(num));
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> first = new HashSet<>();
        Set<String> second;
        Set<Long> ids;
        for (int i = 0; i < elementsNumber; i++)
            first.add(Helper.generateRandomString());
        Shortener shortener = new Shortener(strategy);
        Date start = new Date();
        ids = getIds(shortener, first);
        Helper.printMessage(String.valueOf(new Date().getTime() - start.getTime()));
        start = new Date();
        second = getStrings(shortener, ids);
        Helper.printMessage(String.valueOf(new Date().getTime() - start.getTime()));
        if (first.containsAll(second))
            Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
