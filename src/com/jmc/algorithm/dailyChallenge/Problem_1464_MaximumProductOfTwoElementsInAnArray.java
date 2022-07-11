package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/11 16:04
 **/
public class Problem_1464_MaximumProductOfTwoElementsInAnArray {
    public static int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        return (nums[N - 1] - 1) * (nums[N - 2] - 1);
    }

    public static int maxProduct1(int[] nums) {
        int N = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < N; i++) {
            pq.offer(nums[i]);
        }
        return (pq.poll() - 1) * (pq.poll() - 1);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 4, 5};
        System.out.println(maxProduct1(nums));
    }
}
