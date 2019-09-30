package com.javarush.task.task31.task3102;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> queue = new LinkedList<>();
        List<String> list = new ArrayList<>();
        if (new File(root).isDirectory())
            queue.add(new File(root));
        else list.add(root);
        while (!queue.isEmpty()) {
            File currentFile = queue.poll();
            for (File file : currentFile.listFiles())
                if (file.isDirectory())
                    queue.add(file);
                else list.add(file.getAbsolutePath());
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileTree("C:\\всякое"));
    }
}
