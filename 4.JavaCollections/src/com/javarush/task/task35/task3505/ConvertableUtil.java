package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, V> Map<K, V> convert(List<? extends Convertable<K>> list) {
        Map<K, V> result = new HashMap<>();
        for (int i = 0; i < list.size(); i++)
            result.put(list.get(i).getKey(), (V) list.get(i));
        return result;
    }
}
