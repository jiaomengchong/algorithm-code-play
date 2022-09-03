package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/longest-univalue-path/
 */
public class Problem_0687_LongestUnivaluePath {
    int res;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftTemp = 0, rightTemp = 0;
        if (root.left != null && root.left.val == root.val) {
            leftTemp = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightTemp = right + 1;
        }
        res = Math.max(res, leftTemp + rightTemp);
        return Math.max(leftTemp, rightTemp);
    }

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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);

        Problem_0687_LongestUnivaluePath test = new Problem_0687_LongestUnivaluePath();
        System.out.println(test.longestUnivaluePath(root));
    }
}
