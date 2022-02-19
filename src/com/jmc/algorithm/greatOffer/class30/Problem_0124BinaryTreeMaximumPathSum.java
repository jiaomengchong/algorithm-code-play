package com.jmc.algorithm.greatOffer.class30;

/**
 * 二叉树中的最大路径和
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/20 18:53
 */
public class Problem_0124BinaryTreeMaximumPathSum {
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
        private int maxPathSum;
        private int maxPathSumFromHead;

        public Info(int maxPathSum, int maxPathSumFromHead) {
            this.maxPathSum = maxPathSum;
            this.maxPathSumFromHead = maxPathSumFromHead;
        }
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return process(root).maxPathSum;
    }

    private static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int maxPathSumFromHead = head.val;
        if (leftInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, head.val + leftInfo.maxPathSumFromHead);
        }
        if (rightInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, head.val + rightInfo.maxPathSumFromHead);
        }

        int maxPathSum = head.val;
        if (leftInfo != null) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
        }
        if (rightInfo != null) {
            maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
        }

        maxPathSum = Math.max(maxPathSum, maxPathSumFromHead);
        if (leftInfo != null && rightInfo != null && leftInfo.maxPathSumFromHead > 0 && rightInfo.maxPathSumFromHead > 0) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead + head.val);
        }

        return new Info(maxPathSum, maxPathSumFromHead);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root));
    }
}
