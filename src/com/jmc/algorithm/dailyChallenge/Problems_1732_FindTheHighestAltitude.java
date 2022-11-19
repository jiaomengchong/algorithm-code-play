package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-the-highest-altitude
 */
public class Problems_1732_FindTheHighestAltitude {
    public static int largestAltitude(int[] gain) {
        int ans = 0;
        int N = gain.length;
        int[] help = new int[N + 1];
        for (int i = 1; i < help.length; i++) {
            help[i] = help[i - 1] + gain[i - 1];
            ans = Math.max(ans, help[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] gain = new int[]{-4, -3, -2, -1, 4, 3, 2};
        System.out.println(largestAltitude(gain));
    }
}
