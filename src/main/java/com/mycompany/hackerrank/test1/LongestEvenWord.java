/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hackerrank.test1;

/**
 *
 * @author Ustadho_1218
 */
public class LongestEvenWord {

    public static void main(String[] args) {
        String sentence = "is Time to write great code ";
//        String sentence = "Write code for a great time";
        System.out.println(longestEvenWord(sentence));

    }

    private static String longestEvenWord(String str) {
        String[] sentence = str.split(" ");
        int idx = -1;
        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i].length() % 2 == 0) {
                if (idx == -1) {
                    idx = i;
                } else {
                    if (sentence[i].length() > sentence[idx].length()) {
                        idx = i;
                    }
                }
            }
        }
        return idx == -1 ? "00" : sentence[idx];
    }
}
