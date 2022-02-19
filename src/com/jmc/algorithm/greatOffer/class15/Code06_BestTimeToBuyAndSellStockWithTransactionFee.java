package com.jmc.algorithm.greatOffer.class15;

/**
 * 买卖股票的最佳时机含手续费
 * 测试链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/17 18:57
 */
public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {
    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int bestBuy = -prices[0] - fee;
        int bestSell = 0;
        int curBuy, curSell;
        for (int i = 1; i < prices.length; i++) {
            // i位置买
            curBuy = bestSell - prices[i] - fee;
            // i位置卖
            curSell = bestBuy + prices[i];
            bestBuy = Math.max(bestBuy, curBuy);
            bestSell = Math.max(bestSell, curSell);
        }
        return bestSell;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit(prices, fee));
    }
}
