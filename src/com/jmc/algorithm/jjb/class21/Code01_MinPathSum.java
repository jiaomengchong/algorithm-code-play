package com.jmc.algorithm.jjb.class21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/13 10:14
 */
public class Code01_MinPathSum {
    public static int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        int rowSize = grid.length;
        int colSize = grid[0].length;
        int[][] dp = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                dp[i][j] = -1;
            }
        }

        return process(grid, rowSize, colSize, 0, 0, dp);
    }

    private static int process(int[][] matrix, int rowSize, int colSize, int row, int col, int[][] dp) {
        if (row >= rowSize || col >= colSize) {
            return Integer.MAX_VALUE;
        }

        if (dp[row][col] != -1) {
            return dp[row][col];
        }


        if (row == rowSize - 1 && col == colSize - 1) {
            dp[row][col] = matrix[row][col];
            return dp[row][col];
        }

        int p1 = process(matrix, rowSize, colSize, row + 1, col, dp);
        int p2 = process(matrix, rowSize, colSize, row, col + 1, dp);
        int ans = matrix[row][col] + Math.min(p1, p2);
        dp[row][col] = ans;

        return ans;
    }

    public static int minPathSum1(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] dp = new int[M][N];
        dp[0][0] = grid[0][0];
        for (int col = 1; col < N; col++) {
            dp[0][col] = grid[0][col] + dp[0][col - 1];
        }
        for (int row = 1; row < M; row++) {
            dp[row][0] = grid[row][0] + dp[row - 1][0];
        }
        for (int row = 1; row < M; row++) {
            for (int col = 1; col < N; col++) {
                dp[row][col] = grid[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }

        return dp[M - 1][N - 1];
    }

    public static int minPathSum2(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[] arr = new int[N];
        arr[0] = grid[0][0];
        // 第一行
        for (int col = 1; col < N; col++) {
            arr[col] = arr[col - 1] + grid[0][col];
        }
        for (int row = 1; row < M; row++) {
            arr[0] = grid[row][0] + arr[0];
            for (int col = 1; col < N; col++) {
                arr[col] = Math.min(arr[col - 1], arr[col]) + grid[row][col];
            }
        }

        return arr[N - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        int rowSize = 1000;
        int colSize = 1000;
        int[][] matrix = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum(matrix));
        System.out.println(minPathSum1(matrix));
        System.out.println(minPathSum2(matrix));
    }
}
