package com.jmc.algorithm.greatOffer.class34;

/**
 * 井字游戏
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/2 17:34
 **/
public class Problem_0348_TicTacToe {
    public static class TicTacToe {
        private int[][] rows;
        private int[][] cols;
        private int[] leftUp;
        private int[] rightUp;
        private boolean[][] matrix;
        private int N;

        public TicTacToe(int n) {
            // rows[9][1]表示1这个人，在9行下了几个
            rows = new int[n][3];
            // cols[7][2]表示2这个人，在7列下了几个
            cols = new int[n][3];
            // leftUp[1]=5，表示1这个人在左对角线下了5个
            leftUp = new int[3];
            // rightUp[2]=6，表示2这个人在右对角线下了6个
            rightUp = new int[3];
            matrix = new boolean[n][n];
            N = n;
        }

        public int move(int row, int col, int player) {
            if (matrix[row][col]) {
                return 0;
            }

            matrix[row][col] = true;
            rows[row][player]++;
            cols[col][player]++;
            if (row == col) {
                leftUp[player]++;
            }
            if (row + col == N - 1) {
                rightUp[player]++;
            }
            if (rows[row][player] == N || cols[col][player] == N || leftUp[player] == N || rightUp[player] == N) {
                return player;
            }

            return 0;
        }
    }
}
