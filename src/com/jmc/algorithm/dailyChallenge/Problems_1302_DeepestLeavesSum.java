package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/deepest-leaves-sum/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/17 16:53
 **/
public class Problems_1302_DeepestLeavesSum {
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

    public static int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int ans = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                ans += cur.val;
            }
        }
        return ans;
    }
}
