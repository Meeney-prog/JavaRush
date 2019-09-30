package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/
public class Solution extends Thread {

    public Solution() {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            if (e instanceof Error){
                System.out.println("Нельзя дальше работать");
                return;
            }
            if (e instanceof Exception){
                System.out.println("Надо обработать");
                return;
            }
            if (e instanceof Throwable){
                System.out.println("Поживем - увидим");
            }
        });
    }

    public static void main(String[] args) {
    }
}
