package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/available-captures-for-rook/
 */
public class Problem_0999_AvailableCapturesForRook {
    public static int numRookCaptures(char[][] board) {
        int M = board.length;
        int N = board[0].length;
        int ans = 0, startRow = 0, startCol = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'R') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }
        ans += dfs(board, startRow, startCol, 0) ? 1 : 0;
        ans += dfs(board, startRow, startCol, 1) ? 1 : 0;
        ans += dfs(board, startRow, startCol, 2) ? 1 : 0;
        ans += dfs(board, startRow, startCol, 3) ? 1 : 0;
        return ans;
    }

    private static boolean dfs(char[][] board, int startRow, int startCol, int dir) {
        if (startRow < 0 || startRow >= board.length || startCol < 0 || startCol >= board[0].length) {
            return false;
        }

        if (board[startRow][startCol] == 'p') {
            return true;
        }
        if (board[startRow][startCol] == 'B') {
            return false;
        }
        startRow = dir == 0 ? startRow - 1 : (dir == 2 ? startRow + 1 : startRow);
        startCol = dir == 1 ? startCol + 1 : (dir == 3 ? startCol - 1 : startCol);
        return dfs(board, startRow, startCol, dir);
    }

    public static void main(String[] args) {
        // [[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","R",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
        char[][] board = new char[][]{{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','R','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        System.out.println(numRookCaptures(board));
    }
}
