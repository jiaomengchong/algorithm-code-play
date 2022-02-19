package com.jmc.algorithm.greatOffer.class38;

/**
 * 合并二叉树
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/4 23:32
 **/
public class Problem_0617_MergeTwoBinaryTrees {
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

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return process(root1, root2);
    }

    private static TreeNode process(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        int val = root1.val + root2.val;
        TreeNode left = process(root1.left, root2.left);
        TreeNode right = process(root1.right, root2.right);

        return new TreeNode(val, left, right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        TreeNode treeNode = mergeTrees(root1, root2);
        System.out.println(treeNode);
    }
}
