package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (int i = 0; i < threads.length; i++) {
            switch (threads[i].getState()){
                case NEW:
                    threads[i].start();
                    break;
                case BLOCKED:
                case TIMED_WAITING:
                case WAITING:
                    threads[i].interrupt();
                    break;
                case RUNNABLE:
                    threads[i].isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(threads[i].getPriority());
                    break;
            }
        }
    }

    public static void main(String[] args) {
    }
}
