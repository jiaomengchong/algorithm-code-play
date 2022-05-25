package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class Problem_0215_KthLargestElementInAnArray {
    public static int findKthLargest(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (k == 0) {
                break;
            }
            ans = nums[i];
            k--;
        }
        return ans;
    }

    public static int findKthLargest1(int[] nums, int k) {
        // 转化成求第len-k+1小
        int N = nums.length;
        return process(nums, 0, N - 1, N - k);
    }

    private static int process(int[] nums, int L, int R, int k) {
        if (L == R) {
            return nums[L];
        }
        int pivot = L + (int) (Math.random() * (R - L + 1));
        int[] ranges = partition(nums, L, R, nums[pivot]);
        if (k >= ranges[0] && k <= ranges[1]) {
            return nums[ranges[0]];
        } else if (k < ranges[0]) {
            return process(nums, L, ranges[0] - 1, k);
        } else {
            return process(nums, ranges[1] + 1, R, k);
        }
    }

    private static int[] partition(int[] nums, int L, int R, int pivot) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int cur = L;
        int more = R + 1;
        int less = L - 1;
        while (cur < more) {
            if (nums[cur] < pivot) {
                swap(nums, ++less, cur++);
            } else if (nums[cur] > pivot) {
                swap(nums, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        // [3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6]
        // 20
        int[] nums1 = new int[]{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        int k = 20;
        System.out.println(findKthLargest(nums1, k));

        int[] nums2 = new int[]{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        System.out.println(findKthLargest1(nums2, k));
    }
}
