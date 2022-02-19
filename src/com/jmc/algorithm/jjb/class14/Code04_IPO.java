package com.jmc.algorithm.jjb.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数W
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * W表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/18 18:04
 */
public class Code04_IPO {

    public static class Program {
        private int cost;
        private int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o2.profit;
        }
    }

    public static int findMaximizedCapital(int[] costs, int[] profits, int K, int W) {
        if (costs == null || profits == null) {
            return 0;
        }

        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < costs.length; i++) {
            minCostQueue.add(new Program(costs[i], profits[i]));
        }

        for (int i = 0; i < K; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= W) {
                maxProfitQueue.add(minCostQueue.poll());
            }

            if (maxProfitQueue.isEmpty()) {
                return W;
            }

            W += maxProfitQueue.poll().profit;
        }

        return W;
    }

    public static void main(String[] args) {
        int[] costs = {0,1,1};
        int[] profits = {1,2,3};
        int maxIPO = findMaximizedCapital(costs, profits, 2, 0);
        System.out.println(maxIPO);
    }
}
