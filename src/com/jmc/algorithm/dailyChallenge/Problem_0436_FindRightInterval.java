package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/find-right-interval/
 */
public class Problem_0436_FindRightInterval {
    public static int[] findRightInterval(int[][] intervals) {
        // 输入：intervals = [[3,4],[2,3],[1,2]]
        // 输出：[-1,0,1]
        int n = intervals.length;
        int[][] copy = new int[n][2];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i][0] = intervals[i][0];
            // 注意第二列存的是下标
            copy[i][1] = i;
        }
        Arrays.sort(copy, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            int index = bs(copy, intervals[i][1]);
            ans[i] = index;
        }
        return ans;
    }

    private static int bs(int[][] copy, int target) {
        int left = 0;
        int right = copy.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (copy[mid][0] >= target) {
                ans = copy[mid][1];
                right = mid - 1;
            } else if (copy[mid][0] < target) {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {0, 1}, {3, 4}};
        int[] ans = findRightInterval(intervals);
        System.out.println(Arrays.toString(ans));
    }
}
