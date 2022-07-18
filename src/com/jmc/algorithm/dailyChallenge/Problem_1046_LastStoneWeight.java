package com.jmc.algorithm.dailyChallenge;

import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/problems/last-stone-weight/submissions/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/18 15:21
 **/
public class Problem_1046_LastStoneWeight {
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            Integer fMax = pq.poll();
            Integer sMax = pq.poll();
            if (fMax > sMax) {
                pq.offer(fMax - sMax);
            }
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 4, 1, 8};
        System.out.println(lastStoneWeight(nums));
    }
}
