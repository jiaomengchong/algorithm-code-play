package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/remove-element/
 */
public class Problem_0027_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1 && nums[0] == val) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (right > left && nums[right] == val) {
                right--;
            }

            if (right <= 0) {
                return right < 0 ? 0 : (nums[right] == val ? 0 : 1);
            }

            if (left == right) {
                return nums[left] != val ? left + 1 : left;
            }

            if (nums[left] != val) {
                left++;
            } else {
                // 跟右指针交换
                swap(nums, left, right);
                left++;
            }

        }
        return nums[left] != val ? left + 1 : left;
    }

    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }
}
