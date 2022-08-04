package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/4 18:51
 **/
public class Problems_1403_MinimumSubsequenceInNonIncreasingOrder {
    public static List<Integer> minSubsequence(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int sum = 0, pre = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = N - 1; i >= 0; i--) {
            if (pre + nums[i] > sum / 2) {
                ans.add(nums[i]);
                break;
            } else {
                pre += nums[i];
                ans.add(nums[i]);
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,4,7,6,7};
        System.out.println(minSubsequence(nums));
    }
}
