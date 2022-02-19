package com.jmc.algorithm.jjb.class25;

import java.util.Stack;

/**
 * 统计全1子矩形
 * https://leetcode-cn.com/problems/count-submatrices-with-all-ones
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/5 17:39
 */
public class Code05_CountSubmatricesWithAllOnes {
    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int N = mat.length;
        int M = mat[0].length;
        int[] heights = new int[M];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            ans += process(heights);
        }

        return ans;
    }

    public static int process(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int ans = 0;
        int N = heights.length;
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < N; index++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[index]) {
                Integer pop = stack.pop();
                if (heights[pop] > heights[index]) {
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int n = index - left - 1;
                    int down = Math.max(left == -1 ? 0 : heights[left], heights[index]);
                    ans += (heights[pop] - down) * ((n * (n + 1)) >> 1);
                }
            }
            stack.add(index);

        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int n = N - left - 1;
            int down = left == -1 ? 0 : heights[left];
            ans += (heights[pop] - down) * ((n * (n + 1)) >> 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 1, 1, 0}};
        System.out.println(numSubmat(mat));
    }
}
