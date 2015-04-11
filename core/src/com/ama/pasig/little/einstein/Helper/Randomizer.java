package com.ama.pasig.little.einstein.Helper;

import com.ama.pasig.little.einstein.Main.Randomize;

import java.util.Random;

/**
 * Created by frankensteenie on 3/25/2015.
 */
public class Randomizer {
    private static int index;
    private static int[] randomImages;
    public static int[] randmizeArray(int a, int b){
        Random random = new Random();
        int size = b - a + 1;
        int[] array = new int[size];

        for(int i = 0; i < size; i++){
            array[i] = a + i;
        }

        for(int i = 0; i < array.length; i++){
            int randomPosition = random.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return  array;
    }

    public static int getRandomImages(int index){
        return randomImages[index];
    }

    public static void setRandomImages(int[] randomImages){
        Randomizer.randomImages = randomImages;
    }

    public static int getIndex(){
        return index;
    }
}