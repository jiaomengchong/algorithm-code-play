package com.jmc.algorithm.tt_leetcode.problems;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/16 18:04
 */
public class Problem_BT_MaxDepth {

    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        private int height;

        public Info(int height) {
            this.height = height;
        }
    }

    public static int maxDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }

        return process(head).height;
    }

    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(height);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(2);
        head.left.left.left = new TreeNode(2);

        head.right = new TreeNode(2);
        head.right.left = new TreeNode(2);
        head.right.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(2);

        System.out.println(maxDepth(head));
    }
}
