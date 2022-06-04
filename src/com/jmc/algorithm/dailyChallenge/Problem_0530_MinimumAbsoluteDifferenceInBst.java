package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 */
public class Problem_0530_MinimumAbsoluteDifferenceInBst {
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

    public static int getMinimumDifference(TreeNode root) {
        int ans = Integer.MAX_VALUE;
        List<Integer> ret = new ArrayList<>();
        inOrder(root, ret);
        for (int i = 1; i < ret.size(); i++) {
            ans = Math.min(ans, ret.get(i) - ret.get(i - 1));
        }
        return ans;
    }

    private static void inOrder(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        inOrder(root.left, ret);
        ret.add(root.val);
        inOrder(root.right, ret);
    }
}
