package com.jmc.algorithm.greatOffer.class15;

/**
 * 买卖股票的最佳时机III
 * 测试链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/16 19:48
 */
public class Code03_BestTimeToBuyAndSellStockIII {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        return process(2, prices);
    }

    private static int process(int K, int[] prices) {
        int N = prices.length;
        if (K >= (N >> 1)) {
            return allTrans(prices);
        }

        // dp[0][K]=0,0时机买入卖出K次，收益0
        // dp[i][0]=0,i时机0次交易，收益0
        int[][] dp = new int[N][K + 1];
        for (int j = 1; j <= K; j++) {
            // dp[1][j]
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);
            for (int i = 2; i < N; i++) {
                // dp[2][j]
                p1 = dp[i - 1][j];
                best = Math.max(dp[i][j - 1] - prices[i], best);
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }

        return dp[N - 1][K];
    }

    private static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices));
    }
}
