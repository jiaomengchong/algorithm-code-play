package com.jmc.algorithm.greatOffer.class34;

/**
 * 生命游戏
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/22 20:48
 */
public class Problem_0289_GameOfLife {
    public static void gameOfLife(int[][] board) {
        if (board == null) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (neighbour(board, i, j) == 3 || (board[i][j] == 1 && neighbour(board, i, j) == 2)) {
                    board[i][j] |= 2;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private static int neighbour(int[][] board, int i, int j) {
        return func(board, i - 1, j - 1) +
                func(board, i - 1, j) +
                func(board, i - 1, j + 1) +
                func(board, i, j - 1) +
                func(board, i, j + 1) +
                func(board, i + 1, j - 1) +
                func(board, i + 1, j) +
                func(board, i + 1, j + 1);
    }

    private static int func(int[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length && (board[i][j] & 1) == 1 ? 1 : 0;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife(board);
    }
}
