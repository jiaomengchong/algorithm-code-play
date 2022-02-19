package com.jmc.algorithm.greatOffer.class23;

/**
 * 合并石头的最低成本
 * 测试链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/21 18:23
 */
public class Code05_MinimumCostToMergeStones {
    public static int mergeStones1(int[] stones, int k) {
        if (stones == null || stones.length < 1) {
            return -1;
        }

        int N = stones.length;
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        return process1(0, N - 1, 1, stones, k, preSum);
    }

    /**
     * 从L到R区间，枚举L到i搞出一份，i到R搞出K-1份情况
     * 前缀和sum[N+1],[L,R]的和=sum[R+1]-sum[L]
     *
     * @param L
     * @param R
     * @param part   部分堆石头
     * @param stones
     * @param K
     * @return
     */
    private static int process1(int L, int R, int part, int[] stones, int K, int[] preSum) {
        if (L == R) {
            return part == 1 ? 0 : -1;
        }

        if (part == 1) {
            int p1 = process1(L, R, K, stones, K, preSum);
            if (p1 != -1) {
                return p1 + preSum[R + 1] - preSum[L];
            }
            return -1;
        }

        // part>1
        int ans = Integer.MAX_VALUE;
        for (int i = L; i < R; i++) {
            int left = process1(L, i, 1, stones, K, preSum);
            int right = process1(i + 1, R, part - 1, stones, K, preSum);
            if (left == -1 || right == -1) {
                continue;
            }
            ans = Math.min(left + right, ans);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int mergeStones2(int[] stones, int k) {
        int N = stones.length;
        if (stones == null || N == 0 || ((N - 1) % (k - 1) > 1)) {
            return -1;
        }

        int[][][] dp = new int[N][N][k + 1];
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        return process2(0, N - 1, 1, k, preSum, dp);
    }

    private static int process2(int L, int R, int part, int k, int[] preSum, int[][][] dp) {
        if (dp[L][R][part] != 0) {
            return dp[L][R][part];
        }
        if (L == R) {
            dp[L][R][part] = part == 1 ? 0 : -1;
            return dp[L][R][part];
        }
        if (part == 1) {
            int p1 = process2(L, R, k, k, preSum, dp);
            if (p1 != -1) {
                dp[L][R][part] = p1 + preSum[R + 1] - preSum[L];
                return dp[L][R][part];
            }
            dp[L][R][part] = -1;
            return dp[L][R][part];
        }

        int ans = Integer.MAX_VALUE;
        // 常数项优化 mid += k - 1
        for (int mid = L; mid < R; mid += k - 1) {
            int left = process2(L, mid, 1, k, preSum, dp);
            int right = process2(mid + 1, R, part - 1, k, preSum, dp);
            if (left != -1 && right != -1) {
                ans = Math.min(ans, left + right);
            }
        }

        dp[L][R][part] = ans == Integer.MAX_VALUE ? -1 : ans;
        return dp[L][R][part];
    }

    public static void main(String[] args) {
        int[] stones = new int[]{5, 13, 19, 98, 46, 16, 9, 10, 29, 57, 6, 70, 55, 95, 94, 47, 3, 30, 42};
        System.out.println(mergeStones1(stones, 19));
        System.out.println(mergeStones2(stones, 19));
        System.out.println(70 / 11);
    }
}
