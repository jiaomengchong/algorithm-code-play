package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-distance-between-bst-nodes/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/9 15:12
 **/
public class problem_0783_MinimumDistanceBetweenBstNodes {
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

    public static int minDiffInBST(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        process(root, ret);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < ret.size(); i++) {
            ans = Math.min(ans, Math.abs(ret.get(i) - ret.get(i - 1)));
        }
        return ans;
    }

    private static void process(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }

        process(root.left, ret);
        ret.add(root.val);
        process(root.right, ret);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(90);
        root.left = new TreeNode(69);

        root.left.left = new TreeNode(49);
        root.left.right = new TreeNode(89);

        root.left.left.right = new TreeNode(52);
        System.out.println(minDiffInBST(root));
    }
}
