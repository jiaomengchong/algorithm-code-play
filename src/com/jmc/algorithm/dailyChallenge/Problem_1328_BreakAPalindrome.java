package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/break-a-palindrome/
 */
public class Problem_1328_BreakAPalindrome {
    public static String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer(palindrome);
        int N = palindrome.length();
        int index = -1;
        for (int i = 0; i < N / 2; i++) {
            if (palindrome.charAt(i) != 'a') {
                index = i;
                break;
            }
        }

        if (index != -1) {
            sb.setCharAt(index, 'a');
        } else {
            sb.setCharAt(N - 1, 'b');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String palindrome = "abccba";
        System.out.println(breakPalindrome(palindrome));
    }
}
