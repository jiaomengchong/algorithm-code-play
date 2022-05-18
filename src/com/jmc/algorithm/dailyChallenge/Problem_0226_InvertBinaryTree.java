package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：
 */
public class Problem_0226_InvertBinaryTree {
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

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmpLeft = root.left;
        TreeNode tmpRight = root.right;
        root.left = tmpRight;
        root.right = tmpLeft;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        invertTree(root);
        System.out.println("test end!");
    }
}
