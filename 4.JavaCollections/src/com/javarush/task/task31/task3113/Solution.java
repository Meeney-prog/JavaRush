package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());
        reader.close();
        if (!Files.isDirectory(path)) {
            System.out.println(path.toAbsolutePath().toString() + " -  не папка");
        } else {
            MyFileVisitor myFileVisitor = new MyFileVisitor();
            Files.walkFileTree(path, myFileVisitor);
            System.out.println("Всего папок - " + myFileVisitor.getDirectories());
            System.out.println("Всего файлов - " + myFileVisitor.getFiles());
            System.out.println("Общий размер - " + myFileVisitor.getSize());
        }
    }
}

class MyFileVisitor extends SimpleFileVisitor<Path> {
    private int directories = -1;
    private int files = 0;
    private long size = 0;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        files++;
        size += Files.size(file);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        directories++;
        return super.postVisitDirectory(dir, exc);
    }

    public int getDirectories() {
        return directories;
    }

    public int getFiles() {
        return files;
    }

    public long getSize() {
        return size;
    }
}