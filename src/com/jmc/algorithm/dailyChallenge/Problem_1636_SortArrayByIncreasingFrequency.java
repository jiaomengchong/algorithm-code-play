package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency/
 */
public class Problem_1636_SortArrayByIncreasingFrequency {
    public static int[] frequencySort(int[] nums) {
        int N = nums.length, index = 0;
        int[] ans = new int[N];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(treeMap.entrySet());
        Collections.sort(list, new MyComparator());

        for (Map.Entry<Integer, Integer> map : list) {
            for (int i = 0; i < map.getValue(); i++) {
                ans[index++] = map.getKey();
            }
        }
        return ans;
    }

    public static class MyComparator implements Comparator<Map.Entry<Integer, Integer>> {

        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o1.getValue() != o2.getValue() ? o1.getValue() - o2.getValue() : o2.getKey() - o1.getKey();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 3, 2};
        System.out.println(frequencySort(nums));
    }
}
