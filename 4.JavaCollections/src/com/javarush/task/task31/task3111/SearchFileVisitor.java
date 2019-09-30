package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName = "";
    private String partOfContent = "";
    private int minSize = -1;
    private int maxSize = -1;
    boolean[] checkParams = new boolean[4];
    private List<Path> foundFiles = new ArrayList<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        if ((!checkParams[0] || file.getFileName().toString().contains(partOfName))
                && (!checkParams[1] || new String(content).contains(partOfContent))
                && (!checkParams[2] || content.length >= minSize)
                && (!checkParams[3] || content.length <= maxSize))
            foundFiles.add(file);

        return super.visitFile(file, attrs);
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
        checkParams[0] = true;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
        checkParams[1] = true;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
        checkParams[2] = true;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        checkParams[3] = true;
    }
}
