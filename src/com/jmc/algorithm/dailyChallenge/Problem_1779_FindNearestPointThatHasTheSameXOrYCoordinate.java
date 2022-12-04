package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
 */
public class Problem_1779_FindNearestPointThatHasTheSameXOrYCoordinate {
    public static int nearestValidPoint(int x, int y, int[][] points) {
        int ans = -1, dis = Integer.MAX_VALUE;
        int[] coordinate = new int[2];
        int distance = 0;
        for (int i = 0; i < points.length; i++) {
            if (x == points[i][0] || y == points[i][1]) {
                distance = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
                if (distance < dis) {
                    ans = i;
                    coordinate[0] = points[i][0];
                    coordinate[1] = points[i][1];
                    dis = distance;
                } else if (distance == dis && coordinate[0] + coordinate[1] > points[i][0] + points[i][1]) {
                    coordinate[0] = points[i][0];
                    coordinate[1] = points[i][1];
                    ans = i;
                    dis = distance;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int x = 3, y = 4;
        int[][] points = new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}};
        System.out.println(nearestValidPoint(x, y, points));
    }
}
