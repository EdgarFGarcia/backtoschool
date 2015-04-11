package com.ama.pasig.little.einstein.Main;

import java.util.Random;

/**
 * Created by frankensteenie on 2/28/2015.
 */
public class Randomize {

    private static int index;

    public static int[] randomizeArray(int a, int b) {

        // Random number generator
        Random random = new Random();
        int size = b - a + 1;
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = a + i;
        }

        for (int i = 0; i < array.length; i++) {
            int randomPosition = random.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

//    public static int randPosition(int x, int y){
//        Random random = new Random();
//
//        int randomPos = random.nextInt((y - x) +1) + x;
//
//        return randomPos;
//    }

    private static int repeatLetter = 0;

    public static int getRepeatLetter(){
        return repeatLetter;
    }

    public static void setRepeatLetters(int repeatLetter){
        Randomize.repeatLetter = repeatLetter;
    }

    private static int yAxis;

    private static int[] randomNumbers;

    public static void setRandomNumbers(int[] randomNumbers) {
        Randomize.randomNumbers = randomNumbers;
    }

    public static int getRandomNumber(int index) {
        return randomNumbers[index] - 1;
    }

    private static int[] randomImage;

    public static void setRandomImage(int[] randomImage){
        Randomize.randomImage = randomImage;
    }

    public static int getRandomImage(int index){
        return randomImage[index] - 1;
    }

    public static int getIndex(){
        return index;
    }

    public static void setIndex(int index){
        Randomize.index = index;
    }
}