package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private int a1,a2;
        private String s1, s2;
        private boolean b1, b2;

        public Human(int a1, int a2, boolean b1, boolean b2) {
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
            this.b2 = b2;
        }

        public Human(int a1, int a2, boolean b1) {
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
        }

        public Human(int a1, int a2, String s1) {
            this.a1 = a1;
            this.a2 = a2;
            this.s1 = s1;
        }

        public Human(int a1, int a2, String s1, String s2) {
            this.a1 = a1;
            this.a2 = a2;
            this.s1 = s1;
            this.s2 = s2;
        }

        public Human(boolean b1, boolean b2) {
            this.b1 = b1;
            this.b2 = b2;
        }

        public Human(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        public Human(int a1, int a2) {
            this.a1 = a1;
            this.a2 = a2;
        }

        public Human(boolean b1) {
            this.b1 = b1;
        }

        public Human(String s1) {
            this.s1 = s1;
        }

        public Human(int a1) {
            this.a1 = a1;
        }
        // Напишите тут ваши переменные и конструкторы
    }
}
