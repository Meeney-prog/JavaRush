package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            reader.close();
            reader = new BufferedReader(new FileReader(fileName1));
            while (reader.ready()) {
                allLines.add(reader.readLine());
            }
            reader.close();
            reader = new BufferedReader(new FileReader(fileName2));
            while (reader.ready()) {
                forRemoveLines.add(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Solution solution = new Solution();
        try {
            solution.joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
        boolean isAllContains = true;
        for (String s : forRemoveLines){
            if (!allLines.contains(s)){
                isAllContains = false;
            }
        }
        if (isAllContains){
            for (String s : forRemoveLines){
                allLines.remove(allLines.indexOf(s));
            }
        }
        else{
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
