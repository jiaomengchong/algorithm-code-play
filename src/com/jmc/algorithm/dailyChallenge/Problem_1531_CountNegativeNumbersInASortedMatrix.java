package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/12 18:11
 **/
public class Problem_1531_CountNegativeNumbersInASortedMatrix {
    public static int countNegatives(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int countNegatives2(int[][] grid) {
        int ans = 0, M = grid.length, N = grid[0].length;
        for (int i = 0; i < M; i++) {
            int[] cols = grid[i];
            int left = 0, right = N, pos = N;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (cols[mid] < 0) {
                    pos = mid;
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += N - pos;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, -1}, {-1, -1}};
        System.out.println(countNegatives(nums));
        System.out.println(countNegatives2(nums));
    }
}
