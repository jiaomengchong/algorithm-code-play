package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 测试链接：https://leetcode.cn/problems/max-chunks-to-make-sorted/
 */
public class Problem_0769_MaxChunksToMakeSorted {
    public static int maxChunksToSorted(int[] arr) {
        int N = arr.length;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(arr[0]);
        for (int i = 1; i < N; i++) {
            if (deque.peekLast() < arr[i]) {
                deque.offerLast(arr[i]);
            } else {
                int top = deque.peekLast();
                while (!deque.isEmpty() && deque.peekLast() > arr[i]) {
                    deque.pollLast();
                }
                deque.offerLast(top);
            }
        }
        return deque.size();
    }

    public static void main(String[] args) {
        // 1,2,0,3
        // 1,0,2,3,4
        // 2,0,1
        // 0,3,1,2
        int[] arr = new int[]{0,3,1,2};
        System.out.println(maxChunksToSorted(arr));
    }
}
