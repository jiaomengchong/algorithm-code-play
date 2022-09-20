package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
 */
public class Problem_0857_MinimumCostToHireKWorkers {
    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int N = quality.length;
        double ans = Integer.MAX_VALUE;
        int sumQuality = 0;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a.getRatio(), b.getRatio()));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (Worker worker : workers) {
            pq.offer(worker.quality);
            sumQuality += worker.quality;
            if (pq.size() > k) {
                sumQuality -= pq.poll();
            }
            if (pq.size() == k) {
                ans = Math.min(ans, sumQuality * worker.getRatio());
            }
        }
        return ans;
    }

    private static class Worker {
        private int quality;
        private int wage;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
        }

        public double getRatio() {
            return (double) wage / quality;
        }
    }

    public static void main(String[] args) {
        int[] quality = new int[]{10,20,5};
        int[] wage = new int[]{70,50,30};
        int k = 2;
        System.out.println(mincostToHireWorkers(quality, wage, k));
    }
}
