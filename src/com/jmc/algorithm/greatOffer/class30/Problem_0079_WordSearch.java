package com.jmc.algorithm.greatOffer.class30;

/**
 * 单词搜索
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/11 23:31
 */
public class Problem_0079_WordSearch {
    public static boolean exist(char[][] board, String word) {
        if (board == null && word != null) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 深度优先遍历:从board[i][j]出发,上下左右4个方向走,board[i][j]?=word[k]
     *
     * @param board
     * @param word
     * @param i
     * @param j
     * @param k
     * @return
     */
    private static boolean dfs(char[][] board, String word, int i, int j, int k) {
        // word字符已经全部找到,所以来到word长度位置
        if (k == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(k)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = 0;
        if (dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i, j - 1, k + 1) ||
                dfs(board, word, i, j + 1, k + 1)
        ) {
            return true;
        }
        board[i][j] = temp;

        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCEF";
        System.out.println(exist(board, word));
    }
}
