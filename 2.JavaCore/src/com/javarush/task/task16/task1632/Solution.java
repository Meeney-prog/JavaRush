package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) throws InterruptedException{

    }


    public static class Thread1 extends Thread {
        public void run() {
            while (true) {
            }
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            while (true) {
                try {
                    if (this.isInterrupted())
                        throw new InterruptedException();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }

    public static class Thread3 extends Thread {
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Thread4 extends Thread implements Message {
        @Override
        public void showWarning() {
            this.interrupt();
        }

        public void run() {
            while (!this.isInterrupted()) {
            }
        }
    }

    public static class Thread5 extends Thread {
        public static ArrayList<Integer> list = new ArrayList<>();
        public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public void run() {
            try {
                while (true) {
                    String s = reader.readLine();
                    if (!s.equals("N")) {
                        list.add(Integer.parseInt(s));
                    } else {
                        int sum = 0;
                        for (int i = 0; i < list.size(); i++) {
                            sum+=list.get(i);
                        }
                        System.out.println(sum);
                        reader.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
