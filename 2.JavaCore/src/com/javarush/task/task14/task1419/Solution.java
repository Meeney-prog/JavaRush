package com.javarush.task.task14.task1419;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.OptionalDataException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeoutException;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }
        exceptions.add(new IndexOutOfBoundsException());
        exceptions.add(new InterruptedException());
        exceptions.add(new IllegalAccessException());
        exceptions.add(new IllegalMonitorStateException());
        exceptions.add(new IllegalThreadStateException());
        exceptions.add(new IOException());
        exceptions.add(new TooManyListenersException());
        exceptions.add(new RuntimeException());
        exceptions.add(new TimeoutException());
       // exceptions.add(new BrokenBarrierException());
        //напишите тут ваш код

    }
}
