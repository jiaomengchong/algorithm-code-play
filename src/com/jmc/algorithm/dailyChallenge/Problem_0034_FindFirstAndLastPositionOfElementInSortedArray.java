package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/13 21:01
 **/
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int left = binarySearch(nums, target, 1);
        int right = binarySearch(nums, target, -1);
        return new int[]{left, right};
    }

    private static int binarySearch(int[] nums, int target, int leftOrRight) {
        int left = 0;
        int right = nums.length - 1;
        int ret = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                ret = mid;
                // 向左找/向右找
                if (leftOrRight > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return ret;
    }
}
