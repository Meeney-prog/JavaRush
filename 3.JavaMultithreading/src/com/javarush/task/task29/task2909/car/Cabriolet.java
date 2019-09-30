package com.javarush.task.task29.task2909.car;

public class Cabriolet extends Car {
    public Cabriolet(int numberOfPassengers) {
        super(Car.CABRIOLET, numberOfPassengers);
    }


    @Override
    public int getMaxSpeed() {
        return Car.MAX_CABRIOLET_SPEED;
    }
}
