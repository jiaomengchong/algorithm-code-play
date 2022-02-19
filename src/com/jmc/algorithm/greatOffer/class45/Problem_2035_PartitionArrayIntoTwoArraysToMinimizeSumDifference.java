package com.jmc.algorithm.greatOffer.class45;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 将数组分成两个数组并最小化数组和的差
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/22 15:24
 **/
public class Problem_2035_PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    public static int ways1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int half = N >> 1;
        Map<Integer, TreeSet<Integer>> leftMap = new HashMap<>();
        process(nums, 0, half, 0, 0, leftMap);
        Map<Integer, TreeSet<Integer>> rightMap = new HashMap<>();
        process(nums, half, N, 0, 0, rightMap);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int left : leftMap.keySet()) {
            for (int leftNum : leftMap.get(left)) {
                Integer rightNum = rightMap.get(half - left).floor((sum >> 1) - leftNum);
                if (rightNum != null) {
                    int pickNum = leftNum + rightNum;
                    int restNum = sum - pickNum;
                    ans = Math.min(ans, restNum - pickNum);
                }
            }
        }
        return ans;
    }

    private static void process(int[] nums, int index, int end, int pick, int sum, Map<Integer, TreeSet<Integer>> selectMap) {
        if (index == end) {
            if (!selectMap.containsKey(pick)) {
                selectMap.put(pick, new TreeSet());
            }
            selectMap.get(pick).add(sum);
        } else {
            process(nums, index + 1, end, pick, sum, selectMap);
            process(nums, index + 1, end, pick + 1, sum + nums[index], selectMap);
        }
    }

    public static void main(String[] args) {
        int[] num = new int[]{2,-1,0,4,-2,-9};
        System.out.println(ways1(num));
    }
}
