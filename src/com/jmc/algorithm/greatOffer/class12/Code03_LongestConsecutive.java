package com.jmc.algorithm.greatOffer.class12;

import java.util.HashMap;

/**
 * 最长连续序列：
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * 测试链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/7 20:33
 */
public class Code03_LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;
        int pre, pos, all;
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                pre = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                pos = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                all = 1 + pre + pos;
                map.put(num - pre, all);
                map.put(num + pos, all);
                ans = Math.max(ans, all);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }
}
