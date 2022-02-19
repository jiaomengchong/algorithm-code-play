package com.jmc.algorithm.greatOffer.class46;

import java.util.TreeSet;

/**
 * 矩形区域不超过K的最大数值和
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/23 17:01
 **/
public class Problem_0363_MaxSumOfRectangleNoLargeThanK {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int rs = 0; rs < N; rs++) {
            int[] cols = new int[M];
            for (int re = rs; re < N; re++) {
                treeSet.add(0);
                int rowSum = 0;
                for (int i = 0; i < M; i++) {
                    cols[i] += matrix[re][i];
                    rowSum += cols[i];
                    Integer find = treeSet.ceiling(rowSum - k);
                    if (find != null) {
                        ans = Math.max(ans, rowSum - find);
                    }
                    treeSet.add(rowSum);
                }
                treeSet.clear();
            }
        }

        return ans;
    }

    public static int ways(int[] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return Integer.MIN_VALUE;
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i];
            Integer pre = treeSet.ceiling(sum - k);
            if (pre != null) {
                ans = Math.max(ans, sum - pre);
            }
            treeSet.add(sum);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {2,2,-1}
        };
        int k = 0;
        System.out.println(maxSumSubmatrix(matrix, k));
        int[] arr = new int[]{2,2,-1};
        System.out.println(ways(arr, 0));
    }
}
