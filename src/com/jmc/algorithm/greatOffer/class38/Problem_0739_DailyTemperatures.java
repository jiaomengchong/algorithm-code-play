package com.jmc.algorithm.greatOffer.class38;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 每日温度
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/7 20:38
 **/
public class Problem_0739_DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return null;
        }

        int N = temperatures.length;
        int[] ans = new int[N];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.empty() && temperatures[stack.peek().get(0)] < temperatures[i]) {
                List<Integer> popIndex = stack.pop();
                for (Integer index : popIndex) {
                    ans[index] = i - index;
                }
            }
            if (!stack.isEmpty() && temperatures[stack.peek().get(0)] == temperatures[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] temperatures = new int[]{30, 60, 90};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }
}
