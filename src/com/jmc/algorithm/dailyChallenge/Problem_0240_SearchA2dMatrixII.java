package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/search-a-2d-matrix-ii/
 */
public class Problem_0240_SearchA2dMatrixII {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        int M = matrix.length;
        for (int i = 0; i < M; i++) {
            if (bs(matrix[i], target) != -1) {
                return true;
            }
        }
        return false;
    }

    private static int bs(int[] matrix, int target) {
        int L = 0;
        int R = matrix.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (matrix[mid] < target) {
                L = mid + 1;
            } else if (matrix[mid] > target) {
                R = mid - 1;
            } else {
                ans = mid;
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
        System.out.println(searchMatrix1(matrix, target));
    }
}
