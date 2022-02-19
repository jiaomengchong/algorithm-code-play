package com.jmc.algorithm.greatOffer.class15;

/**
 * 最佳买卖股票时机冷冻期
 * 测试链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/17 17:35
 */
public class Code05_BestTimeToBuyAndSellStockWithCooldown {
    public static int maxProfit(int[] prices) {
        int N = prices.length;
        if (prices == null || N == 0) {
            return 0;
        }
        if (N < 2) {
            return 0;
        }
        // buy[i]代表0-i范围，最后的buy动作，产生的最大收益
        // buy[i]有2种情况
        //      1、i不参与buy，即buy[i]=buy[i-1]
        //      2、i参与buy，即i-2发生sell，i-1coolDown
        //      转移方程：buy[i]=Math.max(buy[i-1], sell[i-2]-arr[i])
        // sell[i]代表0-i范围，最后的sell动作，产生的最大收益
        //      1、i不参与sell，即sell[i]=sell[i-1]
        //      2、i参与sell，sell[i]=buy[i-1]+arr[i]
        //      转移方程：sell[i]=Math.max(sell[i-1], buy[i-1]+arr[i])
        int[] buy = new int[N];
        int[] sell = new int[N];
        buy[0] = -prices[0];
        sell[0] = 0;
        buy[1] = Math.max(buy[0], -prices[1]);
        sell[1] = Math.max(sell[0], buy[0] + prices[1]);

        for (int i = 2; i < N; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        return sell[N - 1];
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
    }
}
