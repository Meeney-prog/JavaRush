package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
        ArrayList<Integer> list = new ArrayList<>();
        while (fileReader.ready()){
            list.add(Integer.parseInt(fileReader.readLine()));
        }
        reader.close();
        fileReader.close();
        Collections.sort(list);
        for (Integer num : list){
            if (num%2 == 0)
                System.out.println(num);
        }
    }
}
