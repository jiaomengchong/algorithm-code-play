package com.jmc.algorithm.jjb.class32;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/3/3 16:32
 */
public class Code02_IndexTree2D {
    private int N;
    private int M;
    private int[][] nums;
    private int[][] tree;

    public Code02_IndexTree2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        N = matrix.length;
        M = matrix[0].length;
        nums = new int[N][M];
        tree = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    private void update(int row, int col, int val) {
        if (N == 0 || M == 0) {
            return;
        }
        int add = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= N; i += i & (-i)) {
            for (int j = col + 1; j <= M; j += j & (-j)) {
                tree[i][j] += add;
            }
        }
    }

    private int sum(int row, int col) {
        int ans = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j += j & (-j)) {
                ans += tree[i][j];
            }
        }
        return ans;
    }

    private int regionSum(int row1, int col1, int row2, int col2) {
        if (N == 0 || M == 0) {
            return 0;
        }

        return sum(row2, col2) - sum(row2, col1 - 1) - sum(row1 - 1, col2) + sum(row1 - 1, col1 - 1);
    }
}
