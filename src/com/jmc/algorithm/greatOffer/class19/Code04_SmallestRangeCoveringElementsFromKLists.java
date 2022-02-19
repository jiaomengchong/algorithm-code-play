package com.jmc.algorithm.greatOffer.class19;

import java.util.*;

/**
 * 最小区间
 * 测试链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/4 16:53
 */
public class Code04_SmallestRangeCoveringElementsFromKLists {
    public static class Info {
        private int value;
        private int groupId;
        private int index;

        public Info(int value, int groupId, int index) {
            this.value = value;
            this.groupId = groupId;
            this.index = index;
        }
    }

    public static class MyComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.value == o2.value ? o1.groupId - o2.groupId : o1.value - o2.value;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        TreeSet<Info> treeSet = new TreeSet<>(new MyComparator());
        for (int i = 0; i < N; i++) {
            treeSet.add(new Info(nums.get(i).get(0), i, 0));
        }
        int min = treeSet.first().value;
        int max = treeSet.last().value;

        while (treeSet.size() == N) {
            Info first = treeSet.first();
            Info last = treeSet.last();
            if (max - min > last.value - first.value) {
                min = first.value;
                max = last.value;
            }
            first = treeSet.pollFirst();
            if (nums.get(first.groupId).size() != first.index + 1) {
                treeSet.add(new Info(nums.get(first.groupId).get(first.index + 1), first.groupId, first.index + 1));
            }
        }
        return new int[]{min, max};
    }

    // [1],[2],[3],[4],[5],[6],[7]
    public static void main(String[] args) {
        List<Integer> nums1 = Arrays.asList(1);
        List<Integer> nums2 = Arrays.asList(2);
        List<Integer> nums3 = Arrays.asList(3);
        List<Integer> nums4 = Arrays.asList(4);
        List<Integer> nums5 = Arrays.asList(5);
        List<Integer> nums6 = Arrays.asList(6);
        List<Integer> nums7 = Arrays.asList(7);
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(nums1);
        nums.add(nums2);
        nums.add(nums3);
        nums.add(nums4);
        nums.add(nums5);
        nums.add(nums6);
        nums.add(nums7);

        System.out.println(Arrays.toString(smallestRange(nums)));
    }
}
