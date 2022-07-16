package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/qIsx9U/
 */
public class OfferII_0041_AverageValueOfSlidingWindow {
    class MovingAverage {
        public int windowSize;
        public Queue<Integer> window;
        public double preSum;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
            // 输入：
            //inputs = ["MovingAverage", "next", "next", "next", "next"]
            //inputs = [[3], [1], [10], [3], [5]]
            //输出：
            //[null, 1.0, 5.5, 4.66667, 6.0]
            windowSize = size;
            window = new ArrayDeque();
            preSum = 0;
        }

        public double next(int val) {
            if (windowSize > window.size()) {
                window.offer(val);
            } else {
                preSum -= window.peek();
                window.poll();
                window.offer(val);
            }
            preSum += val;

            return preSum / window.size();
        }
    }
}
