package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/sort-array-by-parity-ii/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/18 16:11
 **/
public class Problem_0922_SortArrayByParityII {
    public static int[] sortArrayByParityII(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int[] even = new int[N >> 1];
        int[] odd = new int[N >> 1];
        int ei = 0, oi = 0;
        for (int i = 0; i < N; i++) {
            if ((nums[i] & 1) == 0) {
                even[ei++] = nums[i];
            } else {
                odd[oi++] = nums[i];
            }
        }

        for (int i = 0; i < N; i++) {
            if ((i & 1) == 0) {
                ans[i] = even[i / 2];
            } else {
                ans[i] = odd[i / 2];
            }
        }
        return ans;
    }

    public static int[] sortArrayByParityII2(int[] nums) {
        int n = nums.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) {
                    j += 2;
                }
                swap(nums, i, j);
            }
        }
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 2, 5, 7};
        System.out.println(Arrays.toString(sortArrayByParityII2(nums)));
    }
}
