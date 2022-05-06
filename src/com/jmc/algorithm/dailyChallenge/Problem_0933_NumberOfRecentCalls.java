package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode-cn.com/problems/number-of-recent-calls/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/6 9:47
 **/
public class Problem_0933_NumberOfRecentCalls {
    static class RecentCounter {
        private Queue<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }

            return queue.size();
        }
    }
}
