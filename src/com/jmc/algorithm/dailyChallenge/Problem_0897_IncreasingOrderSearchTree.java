package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/increasing-order-search-tree/
 */
public class Problem_0897_IncreasingOrderSearchTree {
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

    static List<Integer> inorderList = new ArrayList<>();
    public static TreeNode increasingBST(TreeNode root) {

        inorder(root);
        TreeNode ans = new TreeNode(inorderList.get(0));
        dfs(ans, inorderList, 1);
        return ans;
    }

    private static void dfs(TreeNode ans, List<Integer> inorderList, int index) {
        if (index == inorderList.size()) {
            return;
        }
        ans.right = new TreeNode(inorderList.get(index));
        dfs(ans.right, inorderList, index + 1);
    }

    private static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(8);

        root.left.left.left = new TreeNode(1);
        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(9);

        System.out.println(increasingBST(root));
    }
}
