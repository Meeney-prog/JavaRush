package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static {
        labels.put(12.1,"12");
        labels.put(12.2,"121");
        labels.put(12.3,"1212");
        labels.put(12.4,"122");
        labels.put(12.5,"1221");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
