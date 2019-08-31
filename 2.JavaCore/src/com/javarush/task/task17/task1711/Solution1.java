package com.javarush.task.task17.task1711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {

    public static int even = 0;
    public static int odd = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(buffer.readLine());
        int i = 10;
        while (n % (i / 10) != n) {

            if (i == 10) {
                if ((n % i) % 2 == 0 && (n % i) != 0)
                    even++;
                else if ((n % i) % 2 != 0 && (n % i) != 0)
                    odd++;
            } else {
                if ((((n % i) - (n % (i / 10))) / (i / 10)) % 2 == 0 && (((n % i) - (n % (i / 10))) / (i / 10)) != 0)
                    even++;
                else if ((((n % i) - (n % (i / 10))) / (i / 10)) % 2 != 0 && (((n % i) - (n % (i / 10))) / (i / 10)) != 0)
                    odd++;
            }
            i = i * 10;
        }
        System.out.println("Even: " + even + " Odd: " + odd);


    }}
