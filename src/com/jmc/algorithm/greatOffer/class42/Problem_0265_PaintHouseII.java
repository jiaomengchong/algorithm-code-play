package com.jmc.algorithm.greatOffer.class42;

/**
 * 粉刷房子 II
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * <p>
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n*k 的矩阵来表示的。
 * <p>
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 * <p>
 * 注意：
 * <p>
 * 所有花费均为正整数。
 * <p>
 * 示例：
 * <p>
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 * 或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 * 进阶：
 * <p>
 * 您能否在 O(nk) 的时间复杂度下解决此问题？
 *
 * @Author jmc
 * @Description
 * @Date 2021/10/18 10:55
 **/
public class Problem_0265_PaintHouseII {
    public static int minCost1(int[][] costs) {
        if (costs == null || costs[0].length == 0) {
            return 0;
        }

        return process1(costs, 0, Integer.MAX_VALUE);
    }

    private static int process1(int[][] costs, int index, int preColor) {
        if (index == costs.length) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        if (preColor == Integer.MAX_VALUE) {
            for (int i = 0; i < costs[0].length; i++) {
                int minCost = costs[0][i] + process1(costs, index + 1, i);
                ans = Math.min(ans, minCost);
            }
        } else {
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < costs[0].length; i++) {
                if (preColor != i && minCost > costs[index][i]) {
                    minCost = costs[index][i];
                    preColor = i;
                }
            }
            ans = minCost + process1(costs, index + 1, preColor);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{
                {1, 5, 3},
                {2, 9, 4},
                {12, 19, 14},
                {2, 102, 66},
                {22, 33, 32}};
        System.out.println(minCost1(costs));
    }
}
