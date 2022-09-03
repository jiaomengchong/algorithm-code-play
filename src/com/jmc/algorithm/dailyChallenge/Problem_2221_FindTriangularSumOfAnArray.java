package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-triangular-sum-of-an-array/
 */
public class Problem_2221_FindTriangularSumOfAnArray {
    public static int triangularSum(int[] nums) {
        int ans = 0, N = nums.length;
        int cycle = N;
        for (int k = 0; k < cycle - 1; k++) {
            for (int i = 0; i < N - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            N--;
        }
        return nums[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        System.out.println(triangularSum(nums));
    }
}
