package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode-cn.com/problems/sort-array-by-parity/
 */
public class Problem_0905_SortArrayByParity {
    public static int[] sortArrayByParity(int[] nums) {
        int[] ans = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int evenCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                evenCnt++;
            }
        }

        int[] even = new int[evenCnt];
        int[] odd = new int[nums.length - evenCnt];
        int evenIndex = 0;
        int oddIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                even[evenIndex++] = nums[i];
            } else {
                odd[oddIndex++] = nums[i];
            }
        }

        for (int i = 0; i < evenCnt; i++) {
            ans[i] = even[i];
        }
        for (int i = evenCnt; i < nums.length; i++) {
            ans[i] = odd[i - evenCnt];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 4};
        int[] ans = sortArrayByParity(nums);
        System.out.println(Arrays.toString(ans));

        nums = new int[]{0};
        System.out.println(Arrays.toString(sortArrayByParity(nums)));
    }
}
