package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName2));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1));
        while (reader.ready()) {
            writer.write(reader.readLine());
            if (reader.ready()) {
                writer.newLine();
            }
        }
        reader.close();
        reader = new BufferedReader(new FileReader(fileName3));
        while (reader.ready()) {
            writer.write(reader.readLine());
            if (reader.ready()) {
                writer.newLine();
            }
        }
        reader.close();
        writer.close();
    }
}
