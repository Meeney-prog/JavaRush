package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;
    static {
        readKeyFromConsoleAndInitPlanet();
    }

    //add static block here - добавьте статический блок тут

    public static void readKeyFromConsoleAndInitPlanet() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            reader.close();
            thePlanet = null;
            if (s.equals(Planet.MOON)){
                thePlanet = ((Moon)thePlanet).getInstance();
            }
            if(s.equals(Planet.EARTH)){
                thePlanet = ((Earth)thePlanet).getInstance();
            }
            if(s.equals(Planet.SUN)){
                thePlanet = ((Sun)thePlanet).getInstance();
            }
        }catch (IOException e){
            e.getStackTrace();
        }

        // implement step #5 here - реализуйте задание №5 тут
    }
}
