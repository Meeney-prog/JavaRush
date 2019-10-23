package com.javarush.task.task26.task2613;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!map.containsKey(currencyCode.toUpperCase()))
            map.put(currencyCode.toUpperCase(), new CurrencyManipulator(currencyCode.toUpperCase()));
        return map.get(currencyCode.toUpperCase());
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return new ArrayList<>(map.values());
    }

    public static Map<String, CurrencyManipulator> getMap() {
        return map;
    }
}
