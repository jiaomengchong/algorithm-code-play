package com.jmc.algorithm.dailyChallenge;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class Problem_0007_ReverseInteger {
    public static int reverse(int x) {
        if (x == 0 || x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            return 0;
        }

        boolean negative = x < 0;
        char[] chars = String.valueOf(Math.abs(x)).toCharArray();
        int N = chars.length;
        int index = 0;
        char[] ans = new char[chars.length];
        for (int i = N - 1; i >= 0; i--) {
            ans[index++] = chars[i];
        }

        Integer res = 0;
        try {
            res = negative ? 0 - Integer.parseInt(String.valueOf(ans)) : Integer.parseInt(String.valueOf(ans));
        } catch (Exception e) {

        }

        return res;
    }

    public static int reverse1(int x) {
        if (x == 0 || x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            return 0;
        }

        int res = 0;
        while (x != 0) {
            // 越界判断
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            int mod = x % 10;
            x /= 10;
            res = res * 10 + mod;
        }
        return res;
    }

    public static void main(String[] args) {
        int x = -2147483647;
        System.out.println(reverse(x));
        System.out.println(reverse1(x));
    }
}
