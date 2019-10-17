package com.javarush.task.task37.task3712;

public abstract class Game {
    abstract void prepareForTheGame();
    abstract public void playGame();
    abstract public void congratulateWinner();
    void run(){
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }
}
