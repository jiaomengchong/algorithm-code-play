package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/
 */
public class Problem_1608_SpecialArrayWithXElementsGreaterThanOrEqualX {
    public static int specialArray(int[] nums) {
        int ans = -1;
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = check(mid, nums);
            if (cnt == mid) {
                return mid;
            } else if (cnt < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int check(int mid, int[] nums) {
        int cnt = 0;
        for (int num : nums) {
            cnt += num >= mid ? 1 : 0;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,4,3,0,4};
        System.out.println(specialArray(nums));
    }
}
