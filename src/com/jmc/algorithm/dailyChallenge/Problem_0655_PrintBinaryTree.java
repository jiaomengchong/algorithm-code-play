package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/print-binary-tree/
 */
public class Problem_0655_PrintBinaryTree {

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

    public static List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        int height = getHeight(root);
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        for (int i = 0; i < m; i++) {
            List<String> ret = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                ret.add("");
            }
            ans.add(ret);
        }

        dfs(ans, root, 0, (n - 1) >> 1, height);
        return ans;
    }

    private static void dfs(List<List<String>> ans, TreeNode root, int row, int col, int height) {
        ans.get(row).set(col, String.valueOf(root.val));
        if (root.left != null) {
            dfs(ans, root.left, row + 1, col - (1 << (height - row - 1)), height);
        }
        if (root.right != null) {
            dfs(ans, root.right, row + 1, col + (1 << (height - row - 1)), height);
        }
    }

    private static int getHeight(TreeNode root) {
        int h = 0;
        if (root.left != null) {
            h = Math.max(h, getHeight(root.left) + 1);
        }
        if (root.right != null) {
            h = Math.max(h, getHeight(root.right) + 1);
        }
        return h;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);
        System.out.println(printTree(root));
    }
}
