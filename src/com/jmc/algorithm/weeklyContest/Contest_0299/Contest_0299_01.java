package com.jmc.algorithm.weeklyContest.Contest_0299;

public class Contest_0299_01 {
    public static boolean checkXMatrix(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == j && grid[i][j] == 0) {
                    // 正对角线
                    return false;
                }
                if (i + j == row - 1 && grid[i][j] == 0) {
                    // 反对角线
                    return false;
                }
                if (i != j && i + j != row - 1 && grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{5,7,0},{0,3,1},{0,5,0}};
        System.out.println(checkXMatrix(grid));
    }
}
