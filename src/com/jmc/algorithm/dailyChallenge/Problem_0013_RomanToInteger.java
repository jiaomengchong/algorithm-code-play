package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/roman-to-integer/
 */
public class Problem_0013_RomanToInteger {
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'M') {
                ans += 1000;
                if (i >= 1 && s.charAt(i - 1) == 'C') {
                    ans -= 100;
                }
            } else if (s.charAt(i) == 'D') {
                ans += 500;
                if (i >= 1 && s.charAt(i - 1) == 'C') {
                    ans -= 100;
                }
            } else if (s.charAt(i) == 'C') {
                if (i + 1 < s.length() && s.charAt(i + 1) == 'M') {
                    ans += 900;
                    i += 1;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'D') {
                    ans += 400;
                    i += 1;
                } else if (i >= 1 && s.charAt(i - 1) == 'X') {
                    ans += 90;
                } else {
                    ans += 100;
                }
            } else if (s.charAt(i) == 'L') {
                if (i >= 1 && s.charAt(i - 1) == 'X') {
                    ans += 40;
                }
                ans += 50;
            } else if (s.charAt(i) == 'X') {
                if (i >= 1 && s.charAt(i - 1) == 'I') {
                    ans += 9;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'L') {
                    ans += 40;
                    i += 1;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'C') {
                    ans += 90;
                    i += 1;
                } else {
                    ans += 10;
                }
            } else if (s.charAt(i) == 'V') {
                if (i >= 1 && s.charAt(i - 1) == 'I') {
                    ans += 4;
                } else {
                    ans += 5;
                }
            } else if (s.charAt(i) == 'I') {
                if (i + 1 < s.length() && s.charAt(i + 1) == 'V') {
                    ans += 4;
                    i += 1;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == 'X') {
                    ans += 9;
                    i += 1;
                } else {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "CDXCVIII";
        System.out.println(romanToInt(s));
    }
}
