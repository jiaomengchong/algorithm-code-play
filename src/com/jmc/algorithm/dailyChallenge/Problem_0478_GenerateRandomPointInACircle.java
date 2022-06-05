package com.jmc.algorithm.dailyChallenge;

import java.util.Random;

/**
 * 测试链接：https://leetcode.cn/problems/generate-random-point-in-a-circle/
 */
public class Problem_0478_GenerateRandomPointInACircle {
    class Solution {
        private Random random;
        private double radius, xc, yc;

        public Solution(double radius, double x_center, double y_center) {
            random = new Random();
            this.radius = radius;
            xc = x_center;
            yc = y_center;
        }

        public double[] randPoint() {
            while (true) {
                double x = random.nextDouble() * (2 * radius) - radius;
                double y = random.nextDouble() * (2 * radius) - radius;
                if (x * x + y * y <= radius * radius) {
                    return new double[]{xc + x, yc + y};
                }
            }
        }
    }
}
