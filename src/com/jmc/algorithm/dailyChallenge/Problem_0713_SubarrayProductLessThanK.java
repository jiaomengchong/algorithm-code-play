package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/5 9:55
 **/
public class Problem_0713_SubarrayProductLessThanK {
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int left = 0;
        int right = 0;
        int ans = 0;
        int product = 1;
        // 10,5,2,6
        // 2 + 3 + 2 + 1
        while (left < right || right < N) {
            if (right != N && product * nums[right] < k) {
                product *= nums[right];
                right++;
            } else if (left != right) {
                ans += right - left;
                product /= nums[left];
                left++;
            } else {
                left++;
                right++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,5,2,6};
        int k = 0;
        System.out.println(numSubarrayProductLessThanK(nums, k));
    }
}
