package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "vre", "home", "same", "jjeeop", "jhvok", "lle", "jh");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (String s : words) {
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[0].length; j++) {
                    if (crossword[i][j] == s.charAt(0)) {
                        boolean[] flag = new boolean[8];
                        startX = j;
                        startY = i;
                        boolean tumb = false;
                        int count = 1;
                        for (int k = 1; k < s.length(); k++) {
                            if (!flag[0] && i + k < crossword.length && j + k < crossword[0].length && crossword[i + k][j + k] == s.charAt(k)) {
                                endX = j + k;
                                endY = i + k;
                                count++;
                            } else {
                                flag[0] = true;
                            }

                            if (!flag[1] && i - k >= 0 && j - k >= 0 && crossword[i - k][j - k] == s.charAt(k)) {
                                endX = j - k;
                                endY = i - k;
                                count++;

                            } else {
                                flag[1] = true;
                            }

                            if (!flag[2] && i - k >= 0 && j + k < crossword[0].length && crossword[i - k][j + k] == s.charAt(k)) {
                                endX = j + k;
                                endY = i - k;
                                count++;

                            } else {
                                flag[2] = true;
                            }

                            if (!flag[3] && j - k >= 0 && i + k < crossword.length && crossword[i + k][j - k] == s.charAt(k)) {
                                endX = j - k;
                                endY = i + k;
                                count++;

                            } else {
                                flag[3] = true;
                            }


                            if (!flag[4] && j + k < crossword[0].length && crossword[i][j + k] == s.charAt(k)) {
                                endX = j + k;
                                endY = i;
                                count++;

                            } else {
                                flag[4] = true;
                            }


                            if (!flag[5] && i + k < crossword.length && crossword[i + k][j] == s.charAt(k)) {
                                endX = j;
                                endY = i + k;
                                count++;

                            } else {
                                flag[5] = true;
                            }


                            if (!flag[6] && i - k >= 0 && crossword[i - k][j] == s.charAt(k)) {
                                endX = j;
                                endY = i - k;
                                count++;

                            } else {
                                flag[6] = true;
                            }


                            if (!flag[7] && j - k >= 0 && crossword[i][j - k] == s.charAt(k)) {
                                endX = j - k;
                                endY = i;
                                count++;

                            } else {
                                flag[7] = true;
                            }



                        }
                        for (int l = 0; l < flag.length; l++) {
                            if (flag[l] == false && !tumb) {
                                tumb = true;
                                continue;
                            }
                            if (tumb && flag[l] == false) {
                                tumb = false;
                                break;
                            }
                        }
                        if (tumb && s.length() <= count) {
                            list.add(new Word(s));
                            list.get(list.size() - 1).setStartPoint(startX, startY);
                            list.get(list.size() - 1).setEndPoint(endX, endY);
                        }
                    }

                }
            }


        }
        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
