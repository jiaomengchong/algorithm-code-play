package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class Problem_0462_MinimumMovesToEqualArrayElementsII {
    // 暴力递归
    public static int minMoves2(int[] nums) {
        // 输入：nums = [1,10,2,9]
        // 输出：16
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.min(ans, process(nums, 0, nums[i]));
        }
        return ans;
    }

    private static int process(int[] nums, int index, int base) {
        if (index == nums.length) {
            return 0;
        }
        int ans = 0;
        ans += process(nums, index + 1, base) + Math.abs(nums[index] - base);

        return ans;
    }

    // 排序 O(n*logn)
    public static int minMoves2_(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        int mid = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            ans += Math.abs(nums[i] - nums[mid]);
        }

        return ans;
    }

    // TODO 最优解 bfprt O(n)

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(minMoves2(nums));
    }
}
