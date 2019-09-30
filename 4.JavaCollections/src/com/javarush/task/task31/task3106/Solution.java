package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) {
        File resultFileName = new File(args[0]);
        List<String> list = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
        Collections.sort(list);
        Vector<FileInputStream> files = new Vector<>();
        for (String s : list) {
            try {
                files.addElement(new FileInputStream(s));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fos = new FileOutputStream(resultFileName)) {
            try (ZipInputStream zis = new ZipInputStream(new SequenceInputStream(files.elements()))) {
                byte[] buf = new byte[2048];
                while (zis.getNextEntry() != null) {
                    int b;
                    while ((b = zis.read(buf)) != -1) {
                        fos.write(buf, 0, b);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

