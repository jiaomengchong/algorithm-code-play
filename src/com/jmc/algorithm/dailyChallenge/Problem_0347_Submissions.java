package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/top-k-frequent-elements/submissions/
 * @Author jmc
 * @Description
 * @Date 2022/5/24 16:11
 **/
public class Problem_0347_Submissions {
    // 方法一：有序表
    public static int[] topKFrequent(int[] nums, int k) {
        // 输入: nums = [1,1,1,2,2,3], k = 2
        // 输出: [1,2]
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entryList.add(entry);
        }

        Collections.sort(entryList, new MyComparator());
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = entryList.get(i).getKey();
        }
        return ans;
    }

    public static class MyComparator implements Comparator<Map.Entry<Integer, Integer>> {

        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    }

    // 方法二：大根堆
    public static int[] topKFrequent2(int[] nums, int k) {
        // 输入: nums = [1,1,1,2,2,3], k = 2
        // 输出: [1,2]
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }

    // TODO 基于快排

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
        System.out.println(Arrays.toString(topKFrequent2(nums, k)));
    }
}
