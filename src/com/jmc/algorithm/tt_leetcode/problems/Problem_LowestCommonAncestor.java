package com.jmc.algorithm.tt_leetcode.problems;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/16 18:14
 */
public class Problem_LowestCommonAncestor {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        private boolean findP;
        private boolean findQ;
        private TreeNode ans;

        public Info(boolean findP, boolean findQ, TreeNode ans) {
            this.findP = findP;
            this.findQ = findQ;
            this.ans = ans;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        return process(root, p, q).ans;
    }

    private static Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }

        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);

        boolean findP = root.val == p.val || leftInfo.findP || rightInfo.findP;
        boolean findQ = root.val == q.val || leftInfo.findQ || rightInfo.findQ;
        TreeNode ans = null;

        if (leftInfo.findP && leftInfo.findQ) {
            ans = leftInfo.ans;
        } else if (rightInfo.findP && rightInfo.findQ) {
            ans = rightInfo.ans;
        } else if (findP && findQ) {
            ans = root;
        }

        return new Info(findP, findQ, ans);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(5);
        head.right = new TreeNode(1);

        head.left.left = new TreeNode(6);
        head.left.right = new TreeNode(2);

        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(4);

        head.right.left = new TreeNode(0);
        head.right.right = new TreeNode(8);

        System.out.println(lowestCommonAncestor(head, head.left, head.right).val);
    }
}
