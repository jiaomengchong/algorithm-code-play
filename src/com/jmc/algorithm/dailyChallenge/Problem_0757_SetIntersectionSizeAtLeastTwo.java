package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/set-intersection-size-at-least-two/
 */
public class Problem_0757_SetIntersectionSizeAtLeastTwo {
    public static int intersectionSizeTwo(int[][] intervals) {
        int ans = 2, N = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]);
        int pos = intervals[0][1];
        int pre = pos - 1;
        for (int i = 1; i < N; i++) {
            if (intervals[i][0] > pre) {
                if (intervals[i][0] > pos) {
                    ans += 2;
                    pre = intervals[i][1] - 1;
                    pos = intervals[i][1];
                } else {
                    ans++;
                    pre = pos;
                    pos = intervals[i][1];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [0,1] [1,2] [1,3]
        int[][] intervals = new int[][]{{1,3},{1,2},{0,1}};
        System.out.println(intersectionSizeTwo(intervals));
    }
}
