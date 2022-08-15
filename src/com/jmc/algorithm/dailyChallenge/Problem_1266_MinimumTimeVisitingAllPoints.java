package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-time-visiting-all-points/
 * @Author jmc
 * @Description
 * @Date 2022/8/8 18:05
 **/
public class Problem_1266_MinimumTimeVisitingAllPoints {
    public static int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0, N = points.length;
        int x0 = points[0][0], y0 = points[0][1];
        for (int i = 1; i < N; i++) {
            ans += Math.max(Math.abs(points[i][0] - x0), Math.abs(points[i][1] - y0));
            x0 = points[i][0];
            y0 = points[i][1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,1},{3,4},{-1,0}};
        System.out.println(minTimeToVisitAllPoints(points));
    }
}
