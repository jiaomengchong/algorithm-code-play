package com.jmc.algorithm.greatOffer.class15;

/**
 * 买卖股票的最佳时机II
 * 测试链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/16 11:50
 */
public class Code02_BestTimeToBuyAndSellStockII {
    public static int maxProfit(int[] prices) {
        // prices = [7, 1, 5, 3, 6, 4]
        // 只累加各个波谷与波峰差之和
        // 波谷与波峰又可以分批次累加
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int ans = 0;
        int pre = prices[0];
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - pre);
            pre = prices[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices));
    }
}
