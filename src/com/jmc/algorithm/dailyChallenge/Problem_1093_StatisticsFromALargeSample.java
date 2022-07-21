package com.jmc.algorithm.dailyChallenge;

import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/problems/statistics-from-a-large-sample/
 */
public class Problem_1093_StatisticsFromALargeSample {
    public static double[] sampleStats(int[] count) {
        double[] ans = new double[5];
        double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        double sum = 0;
        int sample = 0;
        int N = count.length;
        PriorityQueue<int[]> maxTime = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> index = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < N; i++) {
            if (count[i] != 0) {
                min = Math.min(min, i);
                max = Math.max(max, i);
                sum += (double) i * count[i];
                sample += count[i];
                maxTime.offer(new int[]{count[i], i});
                index.offer(new int[]{i, count[i]});
            }
        }

        double median = getMedian(sample, index);

        ans[0] = min;
        ans[1] = max;
        ans[2] = sum / sample;
        ans[3] = median;
        ans[4] = maxTime.peek()[1];
        return ans;
    }

    private static double getMedian(int sample, PriorityQueue<int[]> index) {
        int need = (sample & 1) != 0 ? 1 : 2;
        double median = 0;
        double mid = (double) sample / 2;
        double tmp = 0;
        while (!index.isEmpty()) {
            int[] poll = index.poll();
            tmp += poll[1];
            if (tmp > mid) {
                if (need == 2) {
                    median += poll[0] * 2;
                    need -= 2;
                } else {
                    median += poll[0];
                    need--;
                }
            } else if (tmp == mid) {
                median += poll[0];
                need--;
            }
            if (need == 0) {
                break;
            }
        }
        return (sample & 1) != 0 ? median : median / 2;
    }
}
