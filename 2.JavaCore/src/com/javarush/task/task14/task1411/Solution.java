package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;

        //тут цикл по чтению ключей, пункт 1
        while (true) {
            boolean flag = false;
            key = reader.readLine();
            if (key.equals("loser")) {
                person = new Person.Loser();
                flag = true;
            }
            if (key.equals("user")) {
                person = new Person.User();
                flag = true;
            }
            if (key.equals("coder")) {
                person = new Person.Coder();
                flag = true;
            }
            if (key.equals("proger")) {
                person = new Person.Proger();
                flag = true;
            }
            if (!flag) {
                break;
            }
            doWork(person); //вызываем doWork
            //создаем объект, пункт 2


        }
    }

    public static void doWork(Person person) {
        if (person instanceof Person.Loser){
            ((Person.Loser) person).doNothing();
        }
        if (person instanceof Person.Coder){
            ((Person.Coder) person).writeCode();
        }
        if (person instanceof Person.User){
            ((Person.User) person).live();
        }
        if (person instanceof  Person.Proger){
            ((Person.Proger) person).enjoy();
        }
    }
}
