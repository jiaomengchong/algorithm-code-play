package com.jmc.algorithm.weeklyContest.contest_0295;

import java.util.Map;
import java.util.Stack;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-295/problems/steps-to-make-array-non-decreasing/
 */
public class Contest_0295_03 {
    public static int totalSteps(int[] nums) {
        // 输入：nums = [7,14,4,14,13,2,6,13]
        // 输出：3
        Stack<int[]> stack = new Stack<>();
        int N = nums.length;
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            int steps = 0;
            while (!stack.isEmpty() && stack.peek()[0] < nums[i]) {
                int[] pop = stack.pop();
                steps = Math.max(steps + 1, pop[1]);
            }
            stack.push(new int[]{nums[i], steps});
            ans = Math.max(ans, steps);
        }
        return ans;
    }

    public static int minTurns2(int[] arr) {
        int n = arr.length;
        int[][] stack = new int[n][2];
        int stackSize = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int curAns = 0;
            while (stackSize > 0 && stack[stackSize - 1][0] < arr[i]) {
                curAns = Math.max(curAns + 1, stack[--stackSize][1]);
            }
            stack[stackSize][0] = arr[i];
            stack[stackSize++][1] = curAns;
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,14,4,14,13,2,6,13};
        System.out.println(totalSteps(nums));
        System.out.println(minTurns2(nums));
    }
}
