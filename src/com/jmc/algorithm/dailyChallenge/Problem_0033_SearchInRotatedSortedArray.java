package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/search-in-rotated-sorted-array/
 */
public class Problem_0033_SearchInRotatedSortedArray {

    public static int search(int[] nums, int target) {
        // 输入：nums = [4,5,6,7,0,1,2], target = 0
        // 输出：4
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                index = i;
                break;
            }
        }

        int left = binarySearch(nums, target, 0, index - 1);
        if (left >= 0) {
            return left;
        }

        int right = binarySearch(nums, target, index, nums.length - 1);
        return right;
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        int ret = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }
}
