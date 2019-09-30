package com.javarush.task.task26.task2603;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparator) {
            this.comparators = comparator;
        }

        @Override
        public int compare(T o1, T o2)        {
            int result = 0;
            for (Comparator comparator : comparators)            {
                result = comparator.compare(o1, o2);
                if (result != 0){
                    break;
                }
            }
            return result;
        }

        @Override
        public Comparator<T> reversed() {
            return null;
        }

        @Override
        public Comparator<T> thenComparing(Comparator<? super T> other) {
            return null;
        }

        @Override
        public <U> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
            return null;
        }

        @Override
        public <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor) {
            return null;
        }

        @Override
        public Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {
            return null;
        }

        @Override
        public Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor) {
            return null;
        }

        @Override
        public Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> keyExtractor) {
            return null;
        }
    }
}
