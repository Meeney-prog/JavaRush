package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        while (reader.ready()) {
            list1.add(reader.readLine());
        }
        reader.close();
        reader = new BufferedReader(new FileReader(fileName2));
        while (reader.ready()) {
            list2.add(reader.readLine());
        }
        reader.close();
        int i = 0;
        int j = 0;
        while (i < list1.size() || j < list2.size()) {
            if (i < list1.size() && j < list2.size() && list1.get(i).equals(list2.get(j))) {
                lines.add(new LineItem(Type.SAME, list1.get(i)));
                i++;
                j++;
                continue;
            }
            if ((j < list2.size() && i < list1.size() && list1.get(i).equals(list2.get(j + 1))) || i >= list1.size() && j < list2.size()) {
                lines.add(new LineItem(Type.ADDED, list2.get(j)));
                j++;
                continue;
            }
            lines.add(new LineItem(Type.REMOVED, list1.get(i)));
            i++;

        }


    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
