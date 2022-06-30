package com.jmc.algorithm.weeklyContest.Contest_0299;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-299/problems/maximum-score-of-spliced-array/
 */
public class Contest_0299_03 {
    public static int maximumsSplicedArray(int[] nums1, int[] nums2) {
        // s1 = sum1 - (arr1[left1...right1]) + arr2[left2...right2]
        // s1 = sum1 + (diff[left2-left1]...diff[right2-right1])
        int ans1 = process(nums1, nums2);
        int ans2 = process(nums2, nums1);
        return Math.max(ans1, ans2);
    }

    private static int process(int[] arr1, int[] arr2) {
        int ans = 0;
        int N = arr1.length;
        int preSum = 0;
        int s1 = 0;
        for (int i = 0; i < N; i++) {
            preSum += arr1[i];
            s1 = Math.max(s1 + arr2[i] - arr1[i], 0);
            ans = Math.max(ans, s1);
        }
        return preSum + ans;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{60,60,60};
        int[] nums2 = new int[]{10,90,10};
        System.out.println(maximumsSplicedArray(nums1, nums2));
    }
}
