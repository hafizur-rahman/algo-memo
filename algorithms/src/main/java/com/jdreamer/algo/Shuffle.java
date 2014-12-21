package com.jdreamer.algo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Shuffle {
    public static <T> void shuffle(T[] a) {
        Random random = new Random(System.currentTimeMillis());
        for (int i = a.length; i > 0; i--) {
            int rand = random.nextInt(i);
            swap(a, i - 1, rand);
        }
    }

    private static <T> void swap(T[] a, int i, int change) {
        T helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    public static <T> void shuffle(List<T> list) {
        Random random = new Random(System.currentTimeMillis());
        for (int i = list.size(); i > 0; i--) {
            int rand = random.nextInt(i);

            T tmp = list.get(i-1);
            list.set(i-1, list.get(rand));
            list.set(rand, tmp);
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
        shuffle(a);
        for (int i : a) {
            System.out.println(i);
        }

        System.out.println();
        List<Integer> list = Arrays.asList(a);
        shuffle(list);
        for (int i : list) {
            System.out.println(i);
        }
    }
}
