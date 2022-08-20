package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-binary-tree/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/20 17:17
 **/
public class Problem_0654_MaximumBinaryTree {
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        int N = nums.length;
        if (N == 0) {
            return null;
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int index = -1;
        for (int i = 0; i < N; i++) {
            if (max == nums[i]) {
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, index));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, index + 1, N));
        return root;
    }

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

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1};
        TreeNode ans = constructMaximumBinaryTree(arr);
        System.out.println(ans);
    }
}
