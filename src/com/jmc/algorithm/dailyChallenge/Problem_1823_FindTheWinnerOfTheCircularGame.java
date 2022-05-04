package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 */
public class Problem_1823_FindTheWinnerOfTheCircularGame {
    public static int findTheWinner(int n, int k) {
        // n=6 k=5
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        while (queue.size() != 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        int n = 5, k = 2;
        System.out.println(findTheWinner(n, k));
    }
}
