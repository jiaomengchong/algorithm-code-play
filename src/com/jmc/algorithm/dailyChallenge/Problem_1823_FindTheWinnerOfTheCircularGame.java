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

    // 约瑟夫环递归版本
    public static int findTheWinner1(int n, int k) {
        if (n == 1) {
            return n;
        }

        int ans = (findTheWinner1(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }

    public static int findTheWinner2(int n, int k) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = (ans + k) % i;
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        int n = 235, k = 17;
        System.out.println(findTheWinner(n, k));
        System.out.println(findTheWinner1(n, k));
        System.out.println(findTheWinner2(n, k));
    }
}
