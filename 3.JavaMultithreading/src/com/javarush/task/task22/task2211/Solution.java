package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Charset uft8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), windows1251));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), uft8));
        while (reader.ready()){
            String s = reader.readLine();
            writer.write(s);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}
