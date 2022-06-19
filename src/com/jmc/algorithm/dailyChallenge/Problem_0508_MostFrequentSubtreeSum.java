package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/most-frequent-subtree-sum/
 */
public class Problem_0508_MostFrequentSubtreeSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class MyComparator implements Comparator<Map.Entry<Integer, Integer>> {

        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    }

    public static int[] findFrequentTreeSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[]{root.val};
        }

        Map<Integer, Integer> map = new TreeMap<>();
        process(map, root, true);
        List<Map.Entry<Integer, Integer>> sortedMap = new ArrayList<>(map.entrySet());
        Collections.sort(sortedMap, new MyComparator());

        Integer max = sortedMap.get(0).getValue();
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry entry : sortedMap) {
            if (max == entry.getValue()) {
                ans.add((Integer) entry.getKey());
            } else {
                break;
            }
        }

        int[] ret = new int[ans.size()];
        int index = 0;
        for (Integer a : ans) {
            ret[index++] = a;
        }
        return ret;
    }

    private static int process(Map<Integer, Integer> map, TreeNode root, boolean isRoot) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int left = 0;
        if (root.left != null) {
            left = process(map, root.left, false);
            map.put(left, map.getOrDefault(left, 0) + 1);
        }

        int right = 0;
        if (root.right != null) {
            right = process(map, root.right, false);
            map.put(right, map.getOrDefault(right, 0) + 1);
        }

        int ans = root.val + left + right;
        if (isRoot) {
            map.put(ans, map.getOrDefault(ans, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);

        root.left.right.right = new TreeNode(3);
        System.out.println(Arrays.toString(findFrequentTreeSum(root)));
    }
}
