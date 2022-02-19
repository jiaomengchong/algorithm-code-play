package com.jmc.algorithm.tt_leetcode.problems;

/**
 * @Author jmc
 * @Description
 * @Date 2021/12/23 17:59
 **/
public class problem_2108 {
    public static String firstPalindrome(String[] words) {
        for (String word : words) {
            if (checkPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    public static boolean checkPalindrome(String word) {
        char[] chars = word.toCharArray();
        int s = 0;
        int e = chars.length - 1;
        while (s <= e) {
            if (chars[s] != chars[e]) {
                return false;
            }
            s++;
            e--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"notapalindrome","racecar"};
        System.out.println(firstPalindrome(words));
    }
}
