package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/search-insert-position/
 */
public class Problem_0035_SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        // 输入: nums = [1,3,5,6], target = 5
        // 输出: 2
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[ans] < target ? ans + 1 : ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 7;
        System.out.println(searchInsert(nums, target));
    }
}
