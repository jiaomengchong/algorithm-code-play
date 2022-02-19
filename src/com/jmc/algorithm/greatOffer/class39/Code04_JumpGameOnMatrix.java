package com.jmc.algorithm.greatOffer.class39;

/**
 * 来自京东
 * 给定一个二维数组matrix，matrix[i][j] = k代表：
 * 从(i,j)位置可以随意往右跳<=k，或者从(i,j)位置可以随意往下跳<=k步
 * 如果matrix左上角到右下角，至少需要跳多少次
 * 已知matrix中行数n<=5000，列数m<=5000，matrix中值<=5000
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/18 14:37
 **/
public class Code04_JumpGameOnMatrix {
    public static int ways1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        return process1(matrix, 0, 0);
    }

    private static int process1(int[][] matrix, int row, int col) {
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return 0;
        }

        if (matrix[row][col] == 0) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        for (int down = row + 1; down < matrix.length && down - row <= matrix[row][col]; down++) {
            ans = Math.min(ans, process1(matrix, down, col));
        }

        for (int right = col + 1; right < matrix[0].length && right - col <= matrix[row][col]; right++) {
            ans = Math.min(ans, process1(matrix, row, right));
        }
        return ans != Integer.MAX_VALUE ? ans + 1 : Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {5, 6, 7, 3, 0, 5, 4, 5, 2},
                {2, 4, 2, 6, 6, 0, 0, 0, 5}
        };
        System.out.println(ways1(matrix));
    }
}
