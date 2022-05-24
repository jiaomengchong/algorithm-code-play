package com.jmc.algorithm.dailyChallenge;

/**
 * @Author jmc
 * @Description
 * @Date 2022/5/24 17:49
 **/
public class Problem_0965_UnivaluedBinaryTree {
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
        private int value;
        private boolean isUnival;

        public Info(int value, boolean isUnival) {
            this.value = value;
            this.isUnival = isUnival;
        }
    }

    public static boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Info info = process(root);
        return info.isUnival;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        boolean isUnival = true;
        Info left = process(root.left);
        if (null != left && (left.value != root.val || !left.isUnival)) {
            isUnival = false;
        }
        Info right = process(root.right);
        if (null != right && (right.value != root.val || !right.isUnival)) {
            isUnival = false;
        }
        Info info = new Info(root.val, isUnival);
        return info;
    }
}
