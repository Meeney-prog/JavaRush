package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) {
        File resultFileAbsolutePath = new File(args[1]);
        FileUtils.renameFile(resultFileAbsolutePath, new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt"));
        List<File> files = new ArrayList<>();
        fillListWithFilesNames(new File(args[0]), files);
        files.sort(Comparator.comparing(File::getName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFileAbsolutePath.getParent()+"/allFilesContent.txt"))) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while (reader.ready()) {
                        writer.write(reader.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillListWithFilesNames(File path, List<File> files) {
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
                fillListWithFilesNames(file, files);
            } else {
                if (file.length() <= 50 && file.getName().endsWith(".txt")) {
                    files.add(file);
                }
            }
        }
    }
}
