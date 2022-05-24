package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 */
public class Problem_1658_MinimumOperationsToReduceXToZero {
    public static int minOperations(int[] nums, int x) {
        // nums = [1,1,4,2,3], x = 5
        int ans = process(nums, 0, nums.length - 1, x);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private static int process(int[] nums, int left, int right, int rest) {
        if (left == right) {
            return rest == 0 ? 0 : (rest == nums[left] ? 1 : Integer.MAX_VALUE);
        }

        if (rest == 0) {
            return 0;
        }

        if (nums[left] > rest && nums[right] > rest) {
            return Integer.MAX_VALUE;
        }

        int p1 = process(nums, left + 1, right, rest - nums[left]);
        int p2 = process(nums, left, right - 1, rest - nums[right]);
        if (p1 != Integer.MAX_VALUE) {
            p1 += 1;
        }
        if (p2 != Integer.MAX_VALUE) {
            p2 += 1;
        }
        return Math.min(p1, p2);
    }

    public static int minOperations1(int[] nums, int x) {
        // 变向求解最长连续子数组和为sum-x
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
        }

        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        int L = 0, N = nums.length - 1;
        int R = L;
        int ans = -1;
        int curSum = 0;
        while (R <= N && L <= R) {
            curSum += nums[R];
            while (curSum > target && L <= R) {
                curSum -= nums[L++];
            }
            if (curSum == target) {
                ans = Math.max(ans, R - L + 1);
            }
            R++;
        }

        return ans == -1 ? -1 : nums.length - ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,20,1,1,3};
        int x = 10;
        System.out.println(minOperations(nums, x));
        System.out.println(minOperations1(nums, x));
    }
}
