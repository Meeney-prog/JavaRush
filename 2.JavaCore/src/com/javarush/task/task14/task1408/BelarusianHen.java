package com.javarush.task.task14.task1408;

public class BelarusianHen extends Hen {
    public static final String country = Country.BELARUS;
    public static final int egg = 5;
    @Override
    public int getCountOfEggsPerMonth(){return egg;}

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу " + egg + " яиц в месяц.";
    }
}
