package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/projection-area-of-3d-shapes/
 */
public class Problem_0883_ProjectionAreaOf3DShapes {
    public static int projectionArea(int[][] grid) {
        int areaX = 0, areaY = 0, areaZ = 0;
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            int heightX = 0;
            int heightY = 0;
            for (int j = 0; j < n; j++) {
                areaZ += grid[i][j] > 0 ? 1 : 0;
                heightX = Math.max(heightX, grid[i][j]);
                heightY = Math.max(heightY, grid[j][i]);
            }
            areaX += heightX;
            areaY += heightY;
        }

        return areaX + areaY + areaZ;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 2},
                {3, 4}
        };
        System.out.println(projectionArea(grid));
    }
}
