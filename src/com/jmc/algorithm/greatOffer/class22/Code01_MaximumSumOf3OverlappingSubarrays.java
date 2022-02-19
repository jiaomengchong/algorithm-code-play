package com.jmc.algorithm.greatOffer.class22;

import java.util.Arrays;

/**
 * 三个无重叠子数组的最大和
 * 测试链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/19 10:27
 */
public class Code01_MaximumSumOf3OverlappingSubarrays {
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (nums == null || nums.length < 2 * k) {
            return null;
        }

        int N = nums.length;
        int[] left = new int[N];
        int[] range = new int[N];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[0] = sum;

        int max = sum;
        // 左边数组，以k-1结尾的数组下标，开始位置是0
        // [1,2,1,2,6,7,5,1], 2
        left[k - 1] = 0;
        for (int i = k; i < N; i++) {
            sum = sum - nums[i - k] + nums[i];
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }

        // 反方向搞出右边子数组，从N-1出发
        sum = 0;
        int[] right = new int[N];
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        max = sum;
        right[N - k] = N - k;
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum >= max) {
                max = sum;
                right[i] = i;
            }
        }

        int p1 = 0, p2 = 0, p3 = 0;
        max = 0;
        for (int i = k; i < N - 2 * k + 1; i++) {
            int part1 = range[left[i - 1]];
            int part2 = range[i];
            int part3 = range[right[i + k]];
            if (part1 + part2 + part3 > max) {
                max = part1 + part2 + part3;
                p1 = left[i - 1];
                p2 = i;
                p3 = right[i + k];
            }
        }

        int[] ans = new int[]{p1, p2, p3};
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        /*int[] nums = new int[]{7, 13, 20, 19, 19, 2, 10, 1, 1, 19};
        int k = 3;*/
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, k)));
    }
}
