package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-binary-tree-ii/
 */
public class Problem_0998_MaximumBinaryTreeII {
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

    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode cur = root;
        TreeNode node = new TreeNode(val);
        while (cur != null && cur.val > val) {
            parent = cur;
            cur = cur.right;
        }
        if (parent == null) {
            node.left = cur;
            return node;
        } else {
            parent.right = node;
            node.left = cur;
            return root;
        }
    }

    public static void main(String[] args) {
        // [5,2,3,null,1]
        // 4
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(1);

        System.out.println(insertIntoMaxTree(root, 4));
        System.out.println("test end!");
    }
}
