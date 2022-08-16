package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/two-city-scheduling/
 */
public class Problem_1029_TwoCityScheduling {
    public static int twoCitySchedCost(int[][] costs) {
        int ans = 0, N = costs.length;
        int[][] help = new int[N][3];
        for (int i = 0; i < N; i++) {
            help[i][0] = Math.abs(costs[i][0] - costs[i][1]);
            help[i][1] = costs[i][0];
            help[i][2] = costs[i][1];
        }

        Arrays.sort(help, (a, b) -> b[0] - a[0]);
        int aCnt = 0, bCnt = 0;
        for (int[] hl : help) {

            if (bCnt == (N >> 1)) {
                aCnt++;
                ans += hl[1];
            } else if (aCnt == (N >> 1)) {
                bCnt++;
                ans += hl[2];
            } else if (hl[1] <= hl[2]) {
                aCnt++;
                ans += hl[1];
            } else if ( hl[1] > hl[2]) {
                bCnt++;
                ans += hl[2];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{{518,518},{71,971},{121,862},{967,607},{138,754},{513,337},{499,873},{337,387},{647,917},{76,417}};
        System.out.println(twoCitySchedCost(costs));
    }
}
