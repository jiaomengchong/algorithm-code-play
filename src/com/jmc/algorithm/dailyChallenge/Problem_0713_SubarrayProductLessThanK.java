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

    public static int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }

        int left = 0, right = 0;
        int product = 1;
        int ans = 0;
        while (right < nums.length) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            ans += right - left + 1;
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1};
        int k = 1;
        System.out.println(numSubarrayProductLessThanK(nums, k));
        System.out.println(numSubarrayProductLessThanK1(nums, k));
    }
}
