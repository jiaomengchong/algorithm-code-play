package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;
import java.util.Stack;

/**
 * 测试链接：https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/
 */
public class Problem_1475_FinalPricesWithASpecialDiscountInAShop {
    public static int[] finalPrices(int[] prices) {
        int N = prices.length;
        int[] disCount = Arrays.copyOf(prices, N);
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, prices[0]});
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && stack.peek()[1] >= prices[i]) {
                // 打折
                disCount[stack.peek()[0]] = stack.peek()[1] - prices[i];
                stack.pop();
            }
            stack.push(new int[]{i, prices[i]});
        }
        return disCount;
    }

    public static void main(String[] args) {
        // 输入：prices = [8,7,4,2,8,1,7,7,10,1]
        // 输出：[1,3,2,1,7,0,0,6,9,1]
        int[] prices = new int[]{8, 7, 4, 2, 8, 1, 7, 7, 10, 1};
        System.out.println(Arrays.toString(finalPrices(prices)));
    }
}
