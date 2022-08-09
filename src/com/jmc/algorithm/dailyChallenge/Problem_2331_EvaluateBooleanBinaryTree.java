package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/evaluate-boolean-binary-tree/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/9 20:03
 **/
public class Problem_2331_EvaluateBooleanBinaryTree {
    public static boolean evaluateTree(TreeNode root) {
        return process(root) == 1;
    }

    private static int process(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int ans = root.val;
        if (root.left != null && root.right != null) {
            ans = op(root, process(root.left), process(root.right));
        }

        return ans;
    }

    private static int op(TreeNode node, int val1, int val2) {
        int ans;
        if (node.val == 2) {
            ans = val1 | val2;
        } else {
            ans = val1 & val2;
        }
        return ans;
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
}
