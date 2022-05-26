package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2022-05-26 左老师生日
 * 测试链接：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 */
public class Offer_0062_YuanQuanZhongZuiHouShengXiaDeShuZiLcof {
    public static int lastRemaining(int n, int m) {
        // 输入: n = 5, m = 3
        // 输出: 3
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }
        while (queue.size() != 1) {
            for (int i = 0; i < m - 1; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    public static int lastRemaining1(int n, int m) {
        return findTheWinner(n, m);
    }

    public static int findTheWinner(int n, int k) {
        if (n == 1) {
            return 0;
        }

        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans;
    }

    public static void main(String[] args) {
        int n = 100, m = 17;
        System.out.println(lastRemaining(n, m));
        System.out.println(lastRemaining1(n, m));
    }
}
