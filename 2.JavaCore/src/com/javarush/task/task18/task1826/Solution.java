package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        switch (args[0]) {
            case "-e":
            case "-d":
                FileInputStream inputStream = new FileInputStream(args[1]);
                FileOutputStream outputStream = new FileOutputStream(args[2]);
                while (inputStream.available() > 0) {
                    outputStream.write(~inputStream.read());
                }
                inputStream.close();
                outputStream.close();
        }
    }

}
