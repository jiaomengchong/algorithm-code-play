package com.jmc.algorithm.greatOffer.class01;

/**
 * 给定一个二维数组matrix，
 * 你可以从任何位置出发，走向上下左右四个方向
 * 返回能走出来的最长的递增链长度
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/25 19:47
 */
public class Code05_LongestIncreasingPath {
    public static int longestIncreasingPath1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    private static int process1(int[][] m, int i, int j) {
        int up = (i > 0 && m[i][j] < m[i - 1][j]) ? process1(m, i - 1, j) : 0;
        int down = (i < m.length - 1 && m[i][j] < m[i + 1][j]) ? process1(m, i + 1, j) : 0;
        int left = (j > 0 && m[i][j] < m[i][j - 1]) ? process1(m, i, j - 1) : 0;
        int right = (j < m[0].length - 1 && m[i][j] < m[i][j + 1]) ? process1(m, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public static int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    private static int process2(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = (i > 0 && m[i][j] < m[i - 1][j]) ? process2(m, i - 1, j, dp) : 0;
        int down = (i < m.length - 1 && m[i][j] < m[i + 1][j]) ? process2(m, i + 1, j, dp) : 0;
        int left = (j > 0 && m[i][j] < m[i][j - 1]) ? process2(m, i, j - 1, dp) : 0;
        int right = (j < m[0].length - 1 && m[i][j] < m[i][j + 1]) ? process2(m, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath1(matrix));
        System.out.println(longestIncreasingPath2(matrix));
    }
}
