package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/16 15:57
 **/
public class Problem_0532_KDiffPairsInAnArray {
    public static int findPairs(int[] nums, int k) {
        // 输入：nums = [3, 1, 4, 1, 5], k = 2
        // 输出：2
        //解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
        int N = nums.length;
        int ans = 0;

        if (k == 0) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < N; i++) {
                count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                if (entry.getValue() > 1) {
                    ans++;
                }
            }
            return ans;
        }

        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        Map<String, Integer> map = new HashMap<>();
        while (left <= right && right < N) {
            // 1 1 3 4 5
            if (nums[right] - nums[left] == k) {
                if (!map.containsKey(String.format("%s_%s", nums[left], nums[right]))) {
                    map.put(String.format("%s_%s", nums[left], nums[right]), 1);
                    ans++;
                }
                left++;
                right++;
            } else if (nums[right] - nums[left] < k) {
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }
}
