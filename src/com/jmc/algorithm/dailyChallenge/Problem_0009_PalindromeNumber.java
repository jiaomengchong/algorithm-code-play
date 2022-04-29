package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/palindrome-number/
 */
public class Problem_0009_PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        String s = String.valueOf(x);
        int N = s.length();
        char[] origin = s.toCharArray();
        char[] reverse = new char[N];
        int index = 0;
        for (int i = N - 1; i >= 0; i--) {
            reverse[index++] = origin[i];
        }
        for (int i = 0; i < N; i++) {
            if (origin[i] != reverse[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int x = 10;
        System.out.println(isPalindrome(x));
    }
}
