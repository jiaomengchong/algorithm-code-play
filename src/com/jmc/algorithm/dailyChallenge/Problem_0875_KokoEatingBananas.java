package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/koko-eating-bananas/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/7 16:31
 **/
public class Problem_0875_KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        // 输入：piles = [3,6,7,11], h = 8
        // 输出：4
        int N = piles.length;
        Arrays.sort(piles);
        if (h == N) {
            return piles[N - 1];
        }

        int low = 1, high = piles[N - 1];
        int ans = 1;
        while (low <= high) {
            // 防止溢出: (l+r)/2 -> r-(r-l)/2
            int mid = high - ((high - low) >> 1);
            long costTime = getTime(piles, mid);
            if (costTime <= h) {
                // 速度快了
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private static long getTime(int[] piles, int rate) {
        int cost = 0;
        for (int pile : piles) {
            cost += roundUp(pile, rate);
        }
        return cost;
    }

    // 向上取整
    private static int roundUp(int a, int b) {
        return (a + b - 1) / b;
    }

    public static void main(String[] args) {
        int[] piles = new int[]{3,6,7,11};
        int h = 8;
        System.out.println(minEatingSpeed(piles, h));
    }
}
