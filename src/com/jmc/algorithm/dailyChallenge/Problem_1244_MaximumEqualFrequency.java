package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-equal-frequency/
 */
public class Problem_1244_MaximumEqualFrequency {
    public static int maxEqualFreq(int[] nums) {
        int N = nums.length, maxFreq = 0;
        int ans = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (counts.containsKey(nums[i])) {
                freq.put(counts.get(nums[i]), freq.get(counts.get(nums[i])) - 1);
            }
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(maxFreq, counts.get(nums[i]));
            freq.put(counts.get(nums[i]), freq.getOrDefault(counts.get(nums[i]), 0) + 1);
            // 1.都出现1次
            // 2.出现maxFreq/maxFreq-1，并且maxFreq只出现一次
            // 3.出现maxFreq/1，并且出现1次的数只有一个
            boolean isOk = maxFreq == 1 ||
                    (maxFreq * freq.get(maxFreq) + (maxFreq - 1) * freq.get(maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1) ||
                    (maxFreq * freq.get(maxFreq) + 1 == i + 1 && freq.get(1) == 1);
            if (isOk) {
                ans = Math.max(ans, i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [1,1,1,2,2,2]
        int[] nums = new int[]{1, 1, 1, 2, 2, 2};
        System.out.println(maxEqualFreq(nums));
    }
}
