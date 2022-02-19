package com.jmc.algorithm.tt_leetcode.problems;

/**
 * @Author jmc
 * @Description
 * @Date 2021/12/23 20:10
 **/
public class Problem_2110 {
    public static long ways(int[] prices) {
        int N = prices.length;
        long ans = 0;
        for (int i = 1; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (prices[j - 1] == prices[j] + 1) {
                    ans++;
                }
            }
        }

        return prices.length + ans;
    }

    public static long getDescentPeriods(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        if (prices.length == 1) {
            return 1;
        }

        long ans = 0;

        // 利用滑动窗口
        int L = 0;
        int R = 0;
        for (int i = 1; i < prices.length && R < prices.length; i++) {
            if (prices[i - 1] == prices[i] + 1) {
                R++;
            } else {
                while (L < R) {
                    ans += R - L;
                    L++;
                }
                if (L == R) {
                    L++;
                    R++;
                }
            }
        }

        while (L < R) {
            ans += R - L;
            L++;
        }

        return prices.length + ans;
    }

    public static void main(String[] args) {
        //              0   1   2   3  4  5  6  7  8  9  10 11 12 13 14 15
        int[] prices = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 4, 3, 10, 9, 8, 7};
        System.out.println(getDescentPeriods(prices));
        System.out.println(ways(prices));
    }
}
