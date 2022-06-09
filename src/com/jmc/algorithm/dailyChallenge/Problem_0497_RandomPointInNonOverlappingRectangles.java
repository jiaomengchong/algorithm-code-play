package com.jmc.algorithm.dailyChallenge;

import java.util.Random;

/**
 * 测试链接：https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/9 17:26
 **/
public class Problem_0497_RandomPointInNonOverlappingRectangles {
    static class Solution {
        int[][] rects;
        Random random;
        int[] preSum;
        int N;

        public Solution(int[][] rects) {
            this.rects = rects;
            random = new Random();
            N = rects.length;
            preSum = new int[N + 1];
            int[] rect;
            int x1, y1, x2, y2;
            for (int i = 1; i < N + 1; i++) {
                rect = rects[i - 1];
                x1 = rect[0];
                y1 = rect[1];
                x2 = rect[2];
                y2 = rect[3];
                preSum[i] = preSum[i - 1] + (x2 - x1 + 1) * (y2 - y1 + 1);
            }
        }

        public int[] pick() {
            int pickIndex = random.nextInt(preSum[N] + 1);
            int index = binarySearch(rects, pickIndex);
            int[] rect = rects[index - 1];
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
            int nextX = random.nextInt(x2 - x1 + 1);
            int nextY = random.nextInt(y2 - y1 + 1);
            return new int[]{x1 + nextX, y1 + nextY};
        }

        private int binarySearch(int[][] rects, int pickIndex) {
            int l = 0;
            int r = rects.length;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (preSum[mid] >= pickIndex) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return r;
        }
    }
}
