package com.jmc.algorithm.greatOffer.class15;

/**
 * 股票问题一
 * 测试链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/16 10:04
 */
public class Code01_BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        // [7, 1, 5, 3, 6, 4]
        //  0  1  2  3  4  5
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, Math.max(0, prices[i] - min));
            min = Math.min(min, prices[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}
