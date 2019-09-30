package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels = new ArrayList<>();

        public Car() throws Exception {
            //init wheels here
            if (loadWheelNamesFromDB().length > 4) {
                throw new Exception("Too many wheels =(");
            }
            wheels.add(Wheel.valueOf(this.loadWheelNamesFromDB()[0]));
            wheels.add(Wheel.valueOf(this.loadWheelNamesFromDB()[1]));
            wheels.add(Wheel.valueOf(this.loadWheelNamesFromDB()[2]));
            wheels.add(Wheel.valueOf(this.loadWheelNamesFromDB()[3]));
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        try {
            Car car = new Car();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
