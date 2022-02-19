package com.jmc.algorithm.greatOffer.class39;

import java.util.Stack;

/**
 * 来自腾讯
 * 给定一个数组长度为n的arr,求多少个子数组满足：
 * 子数组两端的值，是这个子数组的最小值和次小值，最小值和次小值谁在最左和最右无所谓
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/17 11:56
 **/
public class Code02_ValidSequence {
    public static class Info {
        private int value;
        private int times;

        public Info(int value, int times) {
            this.value = value;
            this.times = times;
        }
    }

    public static int ways1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int ans = 0;
        Stack<Info> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().value > arr[i]) {
                Info info = stack.pop();
                ans += info.times + cnt(info.times);
            }
            if (!stack.isEmpty() && stack.peek().value == arr[i]) {
                stack.peek().times++;
            } else {
                stack.push(new Info(arr[i], 1));
            }
        }

        while (!stack.isEmpty()) {
            ans += cnt(stack.pop().times);
        }

        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().value > arr[i]) {
                Info info = stack.pop();
                ans += info.times;
            }
            if (!stack.isEmpty() && stack.peek().value == arr[i]) {
                stack.peek().times++;
            } else {
                stack.push(new Info(arr[i], 1));
            }
        }

        return ans;
    }

    private static int cnt(int times) {
        return (times - 1) * times / 2;
    }

    public static void main(String[] args) {
        int[] test = new int[]{16, 4, 28, 22, 10, 31, 22, 31, 6, 6, 6, 7};
        System.out.println(ways1(test));
    }
}
