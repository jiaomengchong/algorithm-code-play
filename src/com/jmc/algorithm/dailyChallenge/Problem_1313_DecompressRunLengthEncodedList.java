package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/decompress-run-length-encoded-list/
 */
public class Problem_1313_DecompressRunLengthEncodedList {
    public static int[] decompressRLElist(int[] nums) {
        int N = nums.length, size = 0, index = 0;
        // 输入：nums = [1,2,3,4]
        // 输出：[2,4,4,4]
        for (int i = 0; i < N; i += 2) {
            size += nums[i];
        }
        int[] ans = new int[size];

        for (int i = 0; i < N; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                ans[index++] = nums[i + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{42,39};
        System.out.println(Arrays.toString(decompressRLElist(nums)));
    }
}
