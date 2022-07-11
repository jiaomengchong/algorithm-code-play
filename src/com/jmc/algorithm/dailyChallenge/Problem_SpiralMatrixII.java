package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class Problem_SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        // 输入：n = 3
        // 输出：[[1,2,3],[8,9,4],[7,6,5]]
        int size = n * n;
        int[][] matrix = new int[n][n];
        int cur = 1;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direct = 0;
        int row = 0, col = 0;
        while (cur <= size) {
            matrix[row][col] = cur;
            int nextRow = row + direction[direct][0];
            int nextCol = col + direction[direct][1];
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || matrix[nextRow][nextCol] != 0) {
                direct = (direct + 1) % 4;
            }
            row += direction[direct][0];
            col += direction[direct][1];
            cur++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] matrix = generateMatrix(n);
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }
}
