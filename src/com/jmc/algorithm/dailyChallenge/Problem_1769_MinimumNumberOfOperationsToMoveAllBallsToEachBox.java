package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 */
public class Problem_1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public static int[] minOperations(String boxes) {
        int[] ans = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '0') continue;
            for (int j = 0; j < boxes.length(); j++) {
                ans[j] += Math.abs(i - j);
            }
        }
        return ans;
    }
}
