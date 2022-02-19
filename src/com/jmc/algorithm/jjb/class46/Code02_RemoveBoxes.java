package com.jmc.algorithm.jjb.class46;

/**
 * 移除盒子
 * <p>
 * 测试链接：https://leetcode.com/problems/remove-boxes/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/11 21:13
 */
public class Code02_RemoveBoxes {
    public static int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        return process(boxes, 0, boxes.length - 1, 0);
    }

    private static int process(int[] boxes, int L, int R, int K) {
        if (L > R) {
            return 0;
        }

        int ans = process(boxes, L + 1, R, 0) + (K + 1) * (K + 1);
        for (int i = L + 1; i <= R; i++) {
            if (boxes[i] == boxes[L]) {
                ans = Math.max(ans, process(boxes, L + 1, i - 1, 0) + process(boxes, i, R, K + 1));
            }
        }
        return ans;
    }

    public static int removeBoxesDp(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process1(boxes, 0, N - 1, 0, dp);
    }

    private static int process1(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }

        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }

        int ans = process1(boxes, L + 1, R, 0, dp) + (K + 1) * (K + 1);
        for (int i = L + 1; i <= R; i++) {
            if (boxes[L] == boxes[i]) {
                ans = Math.max(ans, process1(boxes, L + 1, i - 1, 0, dp) + process1(boxes, i, R, K + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    public static int removeBoxesDpBest(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process2(boxes, 0, N - 1, 0, dp);
    }

    private static int process2(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int last = L;
        while ((last + 1 <= R) && boxes[L] == boxes[last + 1]) {
            last++;
        }
        int pre = K + last - L;
        int ans = process2(boxes, last + 1, R, 0, dp) + (pre + 1) * (pre + 1);
        for (int i = last + 2; i <= R; i++) {
            if (boxes[i] == boxes[L] && boxes[i - 1] != boxes[L]) {
                ans = Math.max(ans, process2(boxes, last + 1, i - 1, 0, dp) + process2(boxes, i, R, pre + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] boxes = new int[]{1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 1, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1};
//        int[] boxes = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println(removeBoxesDp(boxes));
        System.out.println(removeBoxesDpBest(boxes));
    }
}
