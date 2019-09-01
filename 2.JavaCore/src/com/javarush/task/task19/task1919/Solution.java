package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        List<Person> list = new ArrayList<>();

        while (reader.ready()) {
            String s = reader.readLine();
            String name = s.split(" ")[0];
            double salary = Double.parseDouble(s.split(" ")[1]);
            list.add(new Person(name, salary));
        }
        reader.close();
        Iterator<Person> it1 = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).name.equals(list.get(j).name)) {
                    list.get(i).salary += list.get(j).salary;
                    list.get(j).name = "DELETE";
                }
            }
        }
        while (it1.hasNext()) {
            if (it1.next().name.equals("DELETE")) {
                it1.remove();
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static class Person implements Comparable {
        private String name;
        private double salary;

        public Person(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        @Override
        public int compareTo(Object p) {
            return this.name.compareTo(((Person) p).name);
        }

        @Override
        public String toString() {
            return this.name + " " + this.salary;
        }
    }

}



