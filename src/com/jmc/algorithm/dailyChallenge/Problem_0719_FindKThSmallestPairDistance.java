package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 */
public class Problem_0719_FindKThSmallestPairDistance {
    public static int smallestDistancePair(int[] nums, int k) {
        // 输入：nums = [1,6,1], k = 3
        // 输出：5
        Arrays.sort(nums);
        int N = nums.length;
        int left = 0;
        int right = nums[N - 1] - nums[0];
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int count = 0;
            for (int r = 0; r < N; r++) {
                int l = binarySearch(nums, r, nums[r] - mid);
                count += r - l;
            }
            if (count >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int binarySearch(int[] nums, int right, int target) {
        int left = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int smallestDistancePair2(int[] nums, int k) {
        int N = nums.length;
        int left = 0;
        int right = nums[N - 1] - nums[0];
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int count = 0;
            for (int i = 0, j = 0; j < N; j++) {
                while (nums[j] - nums[i] > mid) {
                    i++;
                }
                count += j - i;
            }
            if (count >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 6, 1, 25, 23, 3, 6, 8};
        int k = 6;
        System.out.println(smallestDistancePair(nums, k));
        System.out.println(smallestDistancePair2(nums, k));
    }
}
