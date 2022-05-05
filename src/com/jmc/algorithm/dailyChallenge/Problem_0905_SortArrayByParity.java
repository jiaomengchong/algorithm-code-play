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

    // 常数项优化:2次遍历得答案
    public static int[] sortArrayByParity2(int[] nums) {
        int[] ans = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                ans[index++] = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) != 0) {
                ans[index++] = nums[i];
            }
        }
        return ans;
    }

<<<<<<< Updated upstream
    // 常数项优化:1次遍历得答案 双指针
    public static int[] sortArrayByParity3(int[] nums) {
        int[] ans = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int left = 0;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                ans[left++] = nums[i];
            } else {
                ans[right--] = nums[i];
            }
        }
        return ans;
    }

=======
    public static int[] sortArrayByParity2(int[] nums)
>>>>>>> Stashed changes
    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 4};
        int[] ans = sortArrayByParity(nums);
        int[] ans2 = sortArrayByParity2(nums);
        int[] ans3 = sortArrayByParity3(nums);
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(ans2));
        System.out.println(Arrays.toString(ans3));

        nums = new int[]{0};
        System.out.println(Arrays.toString(sortArrayByParity(nums)));
        System.out.println(Arrays.toString(sortArrayByParity2(nums)));
        System.out.println(Arrays.toString(sortArrayByParity3(nums)));
    }
}
