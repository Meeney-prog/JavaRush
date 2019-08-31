package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        String num = reader.readLine();
        reader.close();
        String[] nums = num.split(" ");
        long[] doubleNums = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            doubleNums[i] = Math.round(Double.parseDouble(nums[i]));
            writer.write(doubleNums[i] + " ");
        }
        reader.close();
        writer.close();
    }
}
