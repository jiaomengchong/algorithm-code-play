package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/balanced-binary-tree/
 */
public class Problem_0110_BalancedBinaryTree {

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

    public static class Info {
        private int height;

        public Info(int height) {
            this.height = height;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        Info left = process(root.left);
        Info right = process(root.right);
        return Math.abs(left.height - right.height) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0);
        }

        Info left = process(root.left);
        Info right = process(root.right);
        int height = 1 + Math.max(left.height, right.height);

        return new Info(height);
    }
}
