package com.jmc.algorithm.greatOffer.class14;

/**
 * 缺失的第一个正数
 * 测试链接：https://leetcode.com/problems/first-missing-positive/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/22 18:46
 */
public class Code06_MissingNumber {
    public static int firstMissingPositive(int[] nums) {
        int L = 0;
        int R = nums.length;
        while (L != R) {
            if (nums[L] == L + 1) {
                L++;
            } else if (nums[L] <= L || nums[L] > R || nums[L] == nums[nums[L] - 1]) {
                // 垃圾区
                swap(nums, L, --R);
            } else {
                // 与对应位置交换
                swap(nums, L, nums[L] - 1);
            }
        }
        return L + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,8,9,11,12};
        System.out.println(firstMissingPositive(nums));
    }
}
