package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/closest-dessert-cost/
 */
public class Problem_1774_ClosestDessertCost {
    int ans = Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for (int base : baseCosts) {
            process(0, base, toppingCosts, target);
        }
        return ans;
    }

    private void process(int index, int curCost, int[] toppingCosts, int target) {
        int a = Math.abs(curCost - target);
        int b = Math.abs(ans - target);
        if (a < b) {
            ans = curCost;
        }
        if (a == b && curCost < ans) {
            ans = curCost;
        }
        if (curCost > target) {
            return;
        }

        for (int i = index; i < toppingCosts.length; i++) {
            process(i + 1, curCost + toppingCosts[i], toppingCosts, target);
            process(i + 1, curCost + toppingCosts[i] * 2, toppingCosts, target);
        }
    }

    public static void main(String[] args) {
        Problem_1774_ClosestDessertCost problem = new Problem_1774_ClosestDessertCost();
        int[] baseCosts = new int[]{10}, toppingCosts = new int[]{1};
        int target = 10;
        int ans = problem.closestCost(baseCosts, toppingCosts, target);
        System.out.println(ans);
    }
}
