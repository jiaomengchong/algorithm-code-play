package com.jmc.algorithm.dailyChallenge;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.cn/problems/validate-stack-sequences/
 */
public class Problem_0946_ValidateStackSequences {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length, j = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        int[] pushed = new int[]{1,2,3,4,5};
        int[] poped = new int[]{4,3,5,1,2};
        System.out.println(validateStackSequences(pushed, poped));
    }
}
