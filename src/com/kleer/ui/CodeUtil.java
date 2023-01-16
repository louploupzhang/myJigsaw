package com.kleer.ui;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {
    public static String getCode() {
        //Create the dictionary
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        //Create the string builder for the code
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        //Character part
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        //Digit part
        int num = r.nextInt(10);
        sb.append(num);
        //Convert the sb to array
        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        //Shuffle the array
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;

        return new String(arr);
    }
}
