package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    public static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void updatePerson(String[] args) {
        Date date = null;
        try {
            date = format1.parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        synchronized (allPeople) {
            allPeople.get(Integer.parseInt(args[0])).setName(args[1]);
            allPeople.get(Integer.parseInt(args[0])).setBirthDate(date);
            if (args[2].equals("м")) {
                allPeople.get(Integer.parseInt(args[0])).setSex(Sex.MALE);
            } else {
                allPeople.get(Integer.parseInt(args[0])).setSex(Sex.FEMALE);
            }
        }
    }

    public static void createPerson(String[] args) {
        Date date = null;
        synchronized (allPeople) {
            try {
                date = format1.parse(args[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            allPeople.add(args[1].equals("м") ? Person.createMale(args[0], date) : Person.createFemale(args[0], date));
            System.out.println(allPeople.size() - 1);
        }
    }

    public static void deletePerson(String args) {
        synchronized (allPeople) {
            allPeople.get(Integer.parseInt(args)).setSex(null);
            allPeople.get(Integer.parseInt(args)).setName(null);
            allPeople.get(Integer.parseInt(args)).setBirthDate(null);
        }
    }

    public static void infoPerson(String args) {
        synchronized (allPeople) {
            String name = allPeople.get(Integer.parseInt(args)).getName();
            String sex = allPeople.get(Integer.parseInt(args)).getSex().equals(Sex.MALE) ? "м" : "ж";
            String date = format2.format(allPeople.get(Integer.parseInt(args)).getBirthDate());
            System.out.println(name + " " + sex + " " + date);
        }

    }

    public static void main(String[] args) {
        //start here - начни тут
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 0; i < args.length / 3; i++) {
                        String[] arg = new String[3];
                        for (int j = 0; j < 3; j++) {
                            arg[j] = args[j + 3 * i + 1];
                        }
                        createPerson(arg);
                    }
                    break;
                }
            case "-u":
                synchronized (allPeople) {
                    for (int i = 0; i < args.length / 4; i++) {
                        String[] arg = new String[4];
                        for (int j = 0; j < 4; j++) {
                            arg[j] = args[j + 4 * i + 1];
                        }
                        updatePerson(arg);
                    }
                    break;
                }
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        deletePerson(args[i]);
                    }
                    break;
                }

            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        infoPerson(args[i]);
                    }
                    break;
                }

        }
    }
}
