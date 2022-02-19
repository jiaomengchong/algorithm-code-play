package com.jmc.algorithm.greatOffer.class15;

/**
 * 买卖股票的最佳时机IV
 * 测试链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/16 14:29
 */
public class Code04_BestTimeToBuyAndSellStockIV {
    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // [1, 3, 1, 3, 1, 3]
        //   u  d  u  d  u  最多达到n/2交易次数，即最多达到n/2波峰波谷，如果k大于n/2，就相当于不限制交易次数
        int N = prices.length;
        if (k >= (N >> 1)) {
            return noLimitTrade(prices);
        }

        // dp[8,3] 代表0-8时刻交易3次
        // 1、8不参与交易，dp[7,3]
        // 2、8参与交易
        //      a、8时刻买入，8时刻卖出，dp[8][2]+arr[8]-arr[8]
        //      b、7时刻买入，8时刻卖出，dp[7][2]+arr[8]-arr[7]
        //      ......
        //      c、0时刻买入，8时刻卖出，dp[0][2]+arr[8]-arr[0]

        // dp[9,3] 代表0-9时刻交易3次
        // 1、9不参与交易，dp[8,3]
        // 2、9参与交易
        //      a、9时刻买入，8时刻卖出，dp[9][2]+arr[9]-arr[9]
        //      b、8时刻买入，8时刻卖出，dp[8][2]+arr[9]-arr[8]
        //      ......
        //      c、0时刻买入，8时刻卖出，dp[0][2]+arr[9]-arr[0]
        // 斜率优化替代枚举：
        // best = Math.max(dp[9][2]-arr[9], dp[8][3])
        // dp[9][3] = Math.max(dp[8][3], Math.max(dp[9][2], best+arr[9]))
        int[][] dp = new int[N][k + 1];
        // dp[0][i]=0，代表0时刻买入卖出i次交易
        // dp[i][0]=0，代表i时刻0次交易
        for (int i = 1; i <= k; i++) {
            // 先算出dp[1][i]
            int p1 = dp[0][i];
            int best = Math.max(dp[1][i - 1] - prices[1], dp[0][i - 1] - prices[0]);
            int p2 = best + prices[1];
            dp[1][i] = Math.max(p1, p2);
            for (int j = 2; j < N; j++) {
                p1 = dp[j - 1][i];
                best = Math.max(dp[j][i - 1] - prices[j], best);
                int newP2 = best + prices[j];
                dp[j][i] = Math.max(p1, newP2);
            }
        }
        return dp[N - 1][k];
    }

    private static int noLimitTrade(int[] prices) {
        int pre = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - pre);
            pre = prices[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(k, prices));
    }
}
