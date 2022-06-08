package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/valid-boomerang/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/8 10:54
 **/
public class Problem_1037_ValidBoomerang {
    public static boolean isBoomerang(int[][] points) {
        // |x1 y1 1|
        // |x2 y2 1|
        // |x3 y3 1|
        // 行列式求三角形面积 S = 1/2(x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2)
        // 输入：points = [[1,1],[2,3],[3,2]]
        // 输出：true
        int area = Math.abs(points[0][0] * points[1][1] + points[1][0] * points[2][1] + points[2][0] * points[0][1]
                - points[0][0] * points[2][1] - points[1][0] * points[0][1] - points[2][0] * points[1][1]);
        return area != 0;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{0, 0}, {1, 2}, {0, 1}};
        System.out.println(isBoomerang(points));
    }
}
