package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-peak-element
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/26 15:26
 **/
public class Problem_0162_FindPeakElement {
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        // 输入：nums = [1,2,3,1]
        // 输出：2
        int L = 1;
        int R = nums.length - 1;
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (mid > 0 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,1};
        System.out.println(findPeakElement(nums));
    }
}
