package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/find-target-indices-after-sorting-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/24 23:09
 **/
public class Problem_02089_FindTargetIndicesAfterSortingArray {
    public static List<Integer> targetIndices(int[] nums, int target) {
        // 输入：nums = [1,2,5,2,3], target = 2
        // 输出：[1,2]
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }
            if (nums[i] == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<Integer> targetIndices2(int[] nums, int target) {
        List<Integer> ans = new ArrayList<>();
        int small = 0, equal = 0;
        for (int num : nums) {
            if (num == target) {
                equal++;
            } else if (num < target) {
                small++;
            }
        }

        if (equal == 0) {
            return ans;
        }

        // 区间[small,small+equal)
        for (int i = small; i < small + equal; i++) {
            ans.add(i);
        }
        return ans;
    }
}
