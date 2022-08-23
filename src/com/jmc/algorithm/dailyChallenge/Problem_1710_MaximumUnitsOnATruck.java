package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-units-on-a-truck/
 */
public class Problem_1710_MaximumUnitsOnATruck {
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0, cnt = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            if (cnt + boxTypes[i][0] <= truckSize) {
                ans += boxTypes[i][0] * boxTypes[i][1];
                cnt += boxTypes[i][0];
            } else {
                ans += (truckSize - cnt) * boxTypes[i][1];
                cnt = truckSize;
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [[2,1],[4,4],[3,1],[4,1],[2,4],[3,4],[1,3],[4,3],[5,3],[5,3]]
        // 13
        int[][] boxTypes = new int[][]{{2,1},{4,4},{3,1},{4,1},{2,4},{3,4},{1,3},{4,3},{5,3},{5,3}};
        int truckSize = 13;
        System.out.println(maximumUnits(boxTypes, truckSize));
    }
}
