package com.jmc.algorithm.weeklyContest.Contest_0299;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-299/problems/maximum-score-of-spliced-array/
 */
public class Contest_0299_03 {
    public static int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int[] preSum1 = new int[nums1.length];
        int[] preSum2 = new int[nums2.length];
        preSum1[0] = nums1[0];
        preSum2[0] = nums2[0];
        for (int i = 1; i < nums1.length; i++) {
            preSum1[i] = preSum1[i - 1] + nums1[i];
            preSum2[i] = preSum2[i - 1] + nums2[i];
        }

        int ans = process(nums1, nums2, 0, preSum1, preSum2);
        return 0;
    }

    private static int process(int[] nums1, int[] nums2, int index, int[] preSum1, int[] preSum2) {
        if (index == nums1.length) {
            return 0;
        }



        return 0;
    }
}
