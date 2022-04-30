package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class Problem_0094_BinaryTreeInorderTraversal {

    public class TreeNode {
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

    // 递归版本
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> ans) {
        if (root != null) {
            dfs(root.left, ans);
            ans.add(root.val);
            dfs(root.right, ans);
        }
    }

    // TODO 迭代版本

    // TODO Morris遍历版本

    public static void main(String[] args) {

    }
}
