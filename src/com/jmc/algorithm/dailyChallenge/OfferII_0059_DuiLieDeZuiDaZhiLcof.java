package com.jmc.algorithm.dailyChallenge;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/
 */
public class OfferII_0059_DuiLieDeZuiDaZhiLcof {
    class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }

        public void push_back(int value) {
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.pollLast();
            }
            deque.offerLast(value);
            queue.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int ans = queue.poll();
            if (ans == deque.peekFirst()) {
                deque.pollFirst();
            }
            return ans;
        }
    }
}
