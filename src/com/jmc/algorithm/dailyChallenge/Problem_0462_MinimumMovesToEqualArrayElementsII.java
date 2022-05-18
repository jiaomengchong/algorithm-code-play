package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class Problem_0462_MinimumMovesToEqualArrayElementsII {
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

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(minMoves2(nums));
    }
}
