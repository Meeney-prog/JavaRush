package com.javarush.task.task24.task2410;

import java.util.Scanner;

public class RowAmount {
    public static void main(String[] args) {
        System.out.println("Введите чсло е: ");
        Scanner sc = new Scanner(System.in);
        double e = sc.nextDouble();
        sc.close();
        double sum = 0;
        int n = 0;
        double elem = 1 / Math.pow(2, n) + 1 / Math.pow(3, n);
        while (elem >= e) {
            sum += elem;
            n++;
            elem = 1 / Math.pow(2, n) + 1 / Math.pow(3, n);
        }
        System.out.printf("%.4f\n", sum);
    }
}


