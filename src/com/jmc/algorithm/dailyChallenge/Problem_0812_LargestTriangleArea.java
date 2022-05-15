package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/largest-triangle-area/
 */
public class Problem_0812_LargestTriangleArea {
    public static double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    ans = Math.max(ans, triangleArea(points[i][0], points[i][1], points[j][0], points[j][1], points[k][0], points[k][1]));
                }
            }
        }
        return ans;
    }

    //     |x1 y1 1|
    // 1/2 |x2 y2 1|  ==> 1/2|x1*y2 + x2*y3 + x3*y1 - x1*y3 - x2*y1 - x3*y2|
    //     |x3 y3 1|
    private static double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

    // todo 凸包

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}};
        System.out.println(largestTriangleArea(points));
    }
}
