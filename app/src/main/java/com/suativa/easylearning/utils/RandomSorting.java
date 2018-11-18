package com.suativa.easylearning.utils;

import java.util.Random;

public class RandomSorting {
    public static void shuffleArray(Object input[]) {
        Random r = new Random();
        int newIndex;
        Object temp;
        for (int i = 0; i < input.length; i++) {
            newIndex = r.nextInt(input.length);
            if (newIndex != i) {
                temp = input[i];
                input[i] = input[newIndex];
                input[newIndex] = temp;
            }
        }
    }
}
