package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class Problem_0977_SquaresOfASortedArray {
    public static int[] sortedSquares(int[] nums) {
        // 输入：nums = [-4,-1,0,3,10]
        // 输出：[0,1,9,16,100]
        int N = nums.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = nums[i] * nums[i];
        }
        Arrays.sort(ans);
        return ans;
    }

    // 双指针
    public static int[] sortedSquares1(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int left = 0, right = N - 1;
        int index = N - 1;
        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                ans[index--] = nums[left] * nums[left];
                left++;
            } else {
                ans[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return ans;
    }
}