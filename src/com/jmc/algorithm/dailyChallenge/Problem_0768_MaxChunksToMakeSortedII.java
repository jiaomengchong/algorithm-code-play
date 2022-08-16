package com.jmc.algorithm.dailyChallenge;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
 */
public class Problem_0768_MaxChunksToMakeSortedII {
    public static int maxChunksToSorted(int[] arr) {
        // 输入: arr = [2,1,3,4,4]
        // 输出: 4
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (stack.isEmpty() || stack.peek() <= arr[i]) {
                stack.push(arr[i]);
            } else {
                Integer max = stack.pop();
                while (!stack.isEmpty() && stack.peek() > arr[i]) {
                    stack.pop();
                }
                stack.push(max);
            }
        }

        return stack.size();
    }

    public static void main(String[] args) {
        // [1,1,0,0,1]
        int[] arr = new int[]{1,1,0,0,1};
        System.out.println(maxChunksToSorted(arr));
    }
}
