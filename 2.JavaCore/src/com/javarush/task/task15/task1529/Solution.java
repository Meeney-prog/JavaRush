package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        reset();//add your code here - добавьте код тут
    }

    public static CanFly result;

    public static void reset() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            if (s.equals("helicopter")){
                result = new Helicopter();
                reader.close();
            }else if (s.equals("plane")){
                result = new Plane(Integer.parseInt(reader.readLine()));
                reader.close();
            }
        }catch (IOException e){
            e.getStackTrace();
        }
        //add your code here - добавьте код тут
    }
}
