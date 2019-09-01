package com.javarush.task.task14.task1417;

public class Hrivna extends Money {
    @Override
    public String getCurrencyName(){return "UAH";}

    public Hrivna(double amount) {
        super(amount);
    }
}
