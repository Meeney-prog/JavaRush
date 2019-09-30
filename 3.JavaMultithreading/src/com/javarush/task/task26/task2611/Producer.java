package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        int i = 1;
        try {
            while (true) {
                map.put(i + "", "Some text for " + i++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().setName("thread-" + i);
            System.out.printf("[%s] thread was terminated\n", Thread.currentThread().getName());
        }
    }
}
