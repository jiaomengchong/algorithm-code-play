package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 */
public class Problem_1658_MinimumOperationsToReduceXToZero {
    public static int minOperations(int[] nums, int x) {
        // nums = [1,1,4,2,3], x = 5
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        int rest = x;

        while (left <= right && (nums[left] <= rest || nums[right] <= rest)) {
            if (nums[left] < nums[right]) {
                if (nums[right] <= rest) {
                    rest -= nums[right--];
                } else {
                    rest -= nums[left++];
                }
                ans++;
            } else {
                if (nums[left] <= rest) {
                    rest -= nums[left++];
                } else {
                    rest -= nums[right--];
                }
                ans++;
            }
        }
        return rest == 0 ? ans : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 20, 1, 1, 3};
        int x = 4;
        System.out.println(minOperations(nums, x));
    }
}
