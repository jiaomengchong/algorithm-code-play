package com.jmc.algorithm.jjb.class25;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/2 17:18
 */
public class Code04_MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        int N = matrix.length;
        int M = matrix[0].length;
        int[] heights = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            ans = Math.max(Code03_LargestRectangleInHistogram.largestRectangleArea(heights), ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(matrix));
    }
}
