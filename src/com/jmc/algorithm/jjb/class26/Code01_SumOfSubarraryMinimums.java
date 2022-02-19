package com.jmc.algorithm.jjb.class26;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/sum-of-subarray-minimums/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/7 23:18
 */
public class Code01_SumOfSubarraryMinimums {
    public static int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] nearLeftLessEqualArr = nearLeftLessEqual(arr);
        int[] nearRightLessArr = nearRightLess(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftIndex = nearLeftLessEqualArr[i];
            int rightIndex = nearRightLessArr[i];
            ans += (i - leftIndex) * (rightIndex - i) *(long) arr[i];
        }

        return process(ans);
    }

    private static int[] nearLeftLessEqual(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int index = arr.length - 1; index >= 0; index--) {
            stack.push(index);
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[index]) {
                ans[stack.pop()] = index;
            }
            stack.push(index);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }
        return ans;
    }

    private static int[] nearRightLess(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                ans[stack.pop()] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = arr.length;
        }

        return ans;
    }

    public static int process(long result) {
        double v = Math.pow(10, 9) + 7;
        return (int) (result % v);
    }

    public static void main(String[] args) {
//        int[] arr = {11, 81, 94, 43, 3};
//        int[] arr = {3, 1, 2, 4};
        int[] arr = {72, 40, 83};
        System.out.println(sumSubarrayMins(arr));
    }
}







