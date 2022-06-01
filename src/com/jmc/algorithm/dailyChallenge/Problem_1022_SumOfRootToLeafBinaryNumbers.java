package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers/
 */
public class Problem_1022_SumOfRootToLeafBinaryNumbers {
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

    public static int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return process(root, 0);
    }

    private static int process(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }

        int value = val << 1 | root.val;
        if (root.left == null && root.right == null) {
            return value;
        }

        int left = process(root.left, value);
        int right = process(root.right, value);
        return left + right;
    }
}
