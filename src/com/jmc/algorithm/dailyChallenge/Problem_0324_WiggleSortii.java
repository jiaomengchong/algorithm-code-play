package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/wiggle-sort-ii/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/28 16:50
 **/
public class Problem_0324_WiggleSortii {
    public static void wiggleSort(int[] nums) {
        // 输入：nums = [1,5,1,1,6,4]
        // 输出：[1,6,1,5,1,4]
        Arrays.sort(nums);
        int index = 0, N = nums.length;
        int[] copy = Arrays.copyOf(nums, N);
        int left = (N - 1) / 2, right = N - 1;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                nums[index++] = copy[left--];
            } else {
                nums[index++] = copy[right--];
            }
        }
    }
}
