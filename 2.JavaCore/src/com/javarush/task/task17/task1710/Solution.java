package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        switch (args[0].substring(1)) {
            case "c":
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date bDate = null;
                try {
                    bDate = format.parse(args[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                allPeople.add(args[2].equals("м") ? Person.createMale(args[1], bDate) : Person.createFemale(args[1], bDate));
                System.out.println(allPeople.size() - 1);
                break;
            case "u":
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date bDate1 = null;
                try {
                    bDate1 = format1.parse(args[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                allPeople.get(Integer.parseInt(args[1])).setName(args[2]);
                allPeople.get(Integer.parseInt(args[1])).setBirthDate(bDate1);
                if (args[3].equals("м")) {
                    allPeople.get(Integer.parseInt(args[1])).setSex(Sex.MALE);
                } else{
                    allPeople.get(Integer.parseInt(args[1])).setSex(Sex.FEMALE);
                }
                break;
            case "d":
                allPeople.get(Integer.parseInt(args[1])).setSex(null);
                allPeople.get(Integer.parseInt(args[1])).setName(null);
                allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
                break;
            case "i":
                String name = allPeople.get(Integer.parseInt(args[1])).getName();
                String sex = allPeople.get(Integer.parseInt(args[1])).getSex().equals(Sex.MALE)?"м":"ж";
                Date bd = allPeople.get(Integer.parseInt(args[1])).getBirthDate();
                format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                System.out.println(name+" "+sex+" "+format.format(bd));
        }
    }
}
