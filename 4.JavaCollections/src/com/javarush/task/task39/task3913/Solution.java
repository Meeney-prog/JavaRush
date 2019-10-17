package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:/logs/"));
        //System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(48,null, null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18,null, null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(new Date(), new Date()));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get date for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));

        LogParser logParser2 = new LogParser(Paths.get("c:/logs/"));
        System.out.println(logParser2.getAllSolvedTasksAndTheirNumber(null, null));
    }
}