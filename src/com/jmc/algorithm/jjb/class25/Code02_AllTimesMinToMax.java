package com.jmc.algorithm.jjb.class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/1 11:45
 */
public class Code02_AllTimesMinToMax {
    public static int max1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int ans = Integer.MIN_VALUE;
        Stack<List<Integer>> stack = new Stack<>();
        for (int index = 0; index < arr.length; index++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[index]) {
                List<Integer> popList = stack.pop();
                Integer calIndex = popList.get(popList.size() - 1);
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                ans = Math.max(ans, arr[calIndex] * (leftLessIndex == -1 ? sum[index - 1] : sum[index - 1] - sum[leftLessIndex]));
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[index]) {
                stack.peek().add(index);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            Integer calIndex = popList.get(popList.size() - 1);
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            ans = Math.max(ans, arr[calIndex] * (leftLessIndex == -1 ? sum[arr.length - 1] : sum[arr.length - 1] - sum[leftLessIndex]));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 2, 1};
        System.out.println(max1(arr));
    }
}
