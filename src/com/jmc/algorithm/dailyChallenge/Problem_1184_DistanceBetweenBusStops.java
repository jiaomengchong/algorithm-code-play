package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/distance-between-bus-stops/
 */
public class Problem_1184_DistanceBetweenBusStops {
    public static int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int N = distance.length;
        int ans = Integer.MAX_VALUE;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = start; i < start + 2 * N; i++) {
            queue.add(i % N);
        }

        int sum1 = 0;
        int sum2 = 0;
        while (!queue.isEmpty()) {
            if (sum1 == 0 && start == queue.peek()) {
                while (!queue.isEmpty() && queue.peek() != destination) {
                    sum1 += distance[queue.poll()];
                }
            }
            if (sum2 == 0 && destination == queue.peek()) {
                while (!queue.isEmpty() && queue.peek() != start) {
                    sum2 += distance[queue.poll()];
                }
            }
            if (sum1 != 0 && sum2 != 0) {
                ans = Math.min(ans, Math.min(sum1, sum2));
                break;
            }
        }

        return ans;
    }

        public static int distanceBetweenBusStops2(int[] distance, int start, int destination) {
            int sum1 = 0, sum2 = 0;
            if (start > destination) {
                int tmp = start;
                start = destination;
                destination = tmp;
            }

            for (int i = 0; i < distance.length; i++) {
                if (i >= start && i < destination) {
                    sum1 += distance[i];
                } else {
                    sum2 += distance[i];
                }
            }
            return Math.min(sum1, sum2);
        }

    public static void main(String[] args) {
        // 0 1 2 3 0 1 2 3
        int[] distance = new int[]{1, 2, 3, 4};
        int start = 1, destination = 3;
        System.out.println(distanceBetweenBusStops(distance, start, destination));
        System.out.println(distanceBetweenBusStops2(distance, start, destination));
    }
}
