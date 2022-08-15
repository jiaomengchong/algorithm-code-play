package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/convert-1d-array-into-2d-array/
 */
public class problem_2022_Convert1dArrayInto2dArray {
    public static int[][] construct2DArray(int[] original, int m, int n) {
        int N = original.length;
        int total = m * n;
        if (total != N) {
            return new int[][]{};
        }

        int[][] ans = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[index++];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [1,3,5,2,4,6]
        //2
        //3
        int[] original = new int[]{1,3,5,2,4,6};
        int m = 2, n = 3;
        int[][] ans = construct2DArray(original, m, n);
        System.out.println(ans);
    }
}
