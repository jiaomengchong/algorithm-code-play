package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/binary-tree-pruning/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/21 10:28
 **/
public class Problem_0814_BinaryTreePruning {
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

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        if (root.left != null) {
            root.left =  pruneTree(root.left);
        }
        if (root.right != null) {
            root.right = pruneTree(root.right);
        }
        return root.left == null && root.right == null && root.val == 0 ? null : root;
    }
}
