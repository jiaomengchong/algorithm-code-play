package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/special-positions-in-a-binary-matrix/
 */
public class Problem_1582_SpecialPositionsInABinaryMatrix {
    public static int numSpecial(int[][] mat) {
        int ans = 0;
        int rowLen = mat.length;
        int colLen = mat[0].length;
        int[] rows = new int[rowLen];
        int[] cols = new int[colLen];
        boolean[] stat = new boolean[colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                rows[i] += mat[i][j];
                cols[j] += mat[i][j];
            }
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (!stat[j] && mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
