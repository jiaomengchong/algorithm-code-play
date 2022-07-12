package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/cells-with-odd-values-in-a-matrix/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/12 11:41
 **/
public class Problem_1252_CellsWithOddValuesInAMatrix {
    public static int oddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];
        int ans = 0;
        for (int[] ind : indices) {
            for (int i = 0; i < m; i++) {
                matrix[i][ind[1]]++;
            }
            for (int i = 0; i < n; i++) {
                matrix[ind[0]][i]++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] % 2 != 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int m = 2, n = 2;
        int[][] indices = new int[][]{{1, 1}, {0, 0}};
        System.out.println(oddCells(m, n, indices));
    }
}
