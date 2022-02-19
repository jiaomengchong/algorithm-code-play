package com.jmc.algorithm.greatOffer.class34;

import java.util.HashMap;
import java.util.Map;

/**
 * 寻找重复数
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/21 11:16
 */
public class Problem_0287_FindTheDuplicateNumber {
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) > 1) {
                return key;
            }
        }

        return Integer.MAX_VALUE;
    }

    public static int findDuplicate1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return Integer.MAX_VALUE;
        }

        int fast = nums[nums[0]];
        int slow = nums[0];
        while (fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }

        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate1(nums));
    }
}
