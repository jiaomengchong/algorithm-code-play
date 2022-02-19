package com.jmc.algorithm.greatOffer.class41;

import java.util.Arrays;

/**
 * @Author jmc
 * @Description
 * @Date 2021/10/18 10:10
 **/
public class Problem_0031_NextPermutation {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int N = nums.length;
        // 从右往左找第一个左边位置小于右边位置的下标
        int firstLessIndex = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLessIndex = i;
                break;
            }
        }

        if (firstLessIndex < 0) {
            reverse(nums, 0, N - 1);
        } else {
            // 找到右半部分，从右往左到，第一个比firstLessIndex大的下标
            int needSwap = N - 1;
            for (int i = N - 1; i > firstLessIndex; i--) {
                if (nums[i] > nums[firstLessIndex]) {
                    needSwap = i;
                    break;
                }
            }
            swap(nums, firstLessIndex, needSwap);
            reverse(nums, firstLessIndex + 1, N - 1);
        }
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
