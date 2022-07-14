package com.jmc.algorithm.dailyChallenge;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.cn/problems/asteroid-collision/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/13 19:36
 **/
public class Problem_0735_AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        int N = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i = 1; i < N; i++) {
            if (!stack.isEmpty() && stack.peek() > 0 && stack.peek() * asteroids[i] < 0) {
                boolean explode = false;
                while (!stack.isEmpty() && stack.peek() * asteroids[i] < 0) {
                    if (Math.abs(stack.peek()) < Math.abs(asteroids[i])) {
                        stack.pop();
                    } else if (Math.abs(stack.peek()) == Math.abs(asteroids[i])) {
                        stack.pop();
                        explode = true;
                        break;
                    } else {
                        explode = true;
                        break;
                    }
                }
                if (!explode) {
                    stack.push(asteroids[i]);
                }
            } else {
                stack.push(asteroids[i]);
            }
        }

        int[] ans = new int[stack.size()];
        int index = stack.size();
        while (!stack.isEmpty()) {
            ans[--index] = stack.pop();
        }
        return ans;
    }
}
