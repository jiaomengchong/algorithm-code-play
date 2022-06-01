package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/matchsticks-to-square/
 */
public class Problem_0473_MatchsticksToSquare {
    public static boolean makesquare(int[] matchsticks) {
        // 输入: matchsticks = [1,1,2,2,2]
        // 输出: true
        int N = matchsticks.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += matchsticks[i];
        }

        if (sum % 4 != 0) {
            return false;
        }

        Arrays.sort(matchsticks);
        for (int i = 0, j = N - 1; i < j; i++, j--) {
            int tmp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = tmp;
        }

        int[] edges = new int[4];
        return dfs(matchsticks, 0, edges, sum / 4);
    }

    private static boolean dfs(int[] matchsticks, int index, int[] edges, int length) {
        if (index == matchsticks.length) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= length && dfs(matchsticks, index + 1, edges, length)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }
}
