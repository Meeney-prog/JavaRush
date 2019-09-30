package com.javarush.task.task29.task2902;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Рефакторинг в соответствии с Naming and Code Convention 2
*/
public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Solution Solution = new Solution();
        String name_of_file_to_be_opened_by_notepad = Solution.Getabsolutepathtodefaulttxtfile().toString();
        Process NOTEPAD = Solution.getstartnotepadprocess(name_of_file_to_be_opened_by_notepad);
        NOTEPAD.waitFor();
    }

    public Process getstartnotepadprocess(String FILE_NAME) throws IOException {
        String[] cmd_array = new String[]{"notepad.exe", FILE_NAME};
        return Runtime.getRuntime().exec(cmd_array);
    }

    public Path Getabsolutepathtodefaulttxtfile() throws URISyntaxException {
        URI uRi = Solution.class.getResource("file.txt").toURI();
        return  Paths.get(uRi);
    }
}
