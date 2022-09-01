package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-zero-filled-subarrays/
 */
public class Problem_2348_NumberOfZeroFilledSubArrays {
    public static long zeroFilledSubarray(int[] nums) {
        int N = nums.length;
        int cnt = nums[0] == 0 ? 1 : 0;
        long ans = cnt;
        for (int i = 1; i < N; i++) {
            if (nums[i] == 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 2, 0, 0};
        System.out.println(zeroFilledSubarray(nums));
    }
}
