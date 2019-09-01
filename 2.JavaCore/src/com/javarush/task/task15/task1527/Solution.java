package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            reader.close();
            String copy = s;
            if(copy.contains("?")){
               copy = copy.substring(copy.indexOf("?")+1);
               boolean flag = true;
                for (int i = 0; i < copy.length(); i++) {
                    if (copy.substring(i,i+1).equals("=")){
                        flag=false;
                        continue;
                    }
                    if (copy.substring(i, i+1).equals("&")){
                        System.out.print(" ");
                        flag = true;
                        continue;
                    }
                    if (flag){
                        System.out.print(copy.substring(i,i+1));
                    }

                }
            }
            ArrayList<String> list = new ArrayList<>();
            System.out.println();
            while (true) {
                if (s.contains("obj")) {
                    String str = "";
                    s = s.substring(s.indexOf("obj") + 4);
                    for (int i = 0; i < s.length(); i++) {
                        if (!s.substring(i, i + 1).equals("&")) {
                            str += s.substring(i, i + 1);
                            continue;
                        }
                        s = s.substring(i + 1);
                        break;
                    }
                    list.add(str);
                }
                else {
                    break;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                try{
                    alert(Double.parseDouble(list.get(i)));
                }catch (Exception e){
                    alert(list.get(i));
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
