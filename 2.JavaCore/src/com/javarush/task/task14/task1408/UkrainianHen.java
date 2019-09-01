package com.javarush.task.task14.task1408;

public class UkrainianHen extends Hen {
    public static final String country = Country.UKRAINE;
    public static final int egg = 3;

    @Override
    public int getCountOfEggsPerMonth() {
        return egg;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу " + egg + " яиц в месяц.";
    }
}
