package com.jmc.algorithm.greatOffer.class30;

/**
 * 对称二叉树
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/15 21:00
 */
public class Problem_0101_SymmetricTree {

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

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return process(root, root);
    }

    private static boolean process(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 != null && node2 == null) {
            return false;
        }

        if (node1 == null && node2 != null) {
            return false;
        }

        return node1.val == node2.val && process(node1.left, node2.right) && process(node1.right, node2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(4);

        System.out.println(isSymmetric(root));
    }
}
