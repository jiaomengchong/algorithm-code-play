package com.jmc.algorithm.weeklyContest.contest_0296;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-296/problems/partition-array-such-that-maximum-difference-is-k/
 */
public class Contest_0296_02 {
    public static int partitionArray(int[] nums, int k) {
        // 输入：nums = [3,6,1,2,5], k = 2
        // 输出：2
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int min = nums[0];
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - min > k) {
                min = nums[i];
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,4,5};
        int k = 0;
        System.out.println(partitionArray(nums, k));
    }
}
