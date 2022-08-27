package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-width-of-binary-tree/
 */
public class Problem_0662_MaximumWidthOfBinaryTree {
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

    public static class Info {
        private TreeNode node;
        private int index;

        public Info(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int ans = 1;
        List<Info> infos = new ArrayList<>();
        infos.add(new Info(root, 1));
        while (infos.size() != 0) {
            List<Info> tmp = new ArrayList<>();
            for (Info info : infos) {
                TreeNode cur = info.node;
                int index = info.index;
                if (cur.left != null) {
                    tmp.add(new Info(cur.left, index << 1));
                }
                if (cur.right != null) {
                    tmp.add(new Info(cur.right, (index << 1) | 1));
                }
            }
            if (tmp.size() != 0) {
                ans = Math.max(ans, tmp.get(tmp.size() - 1).index - tmp.get(0).index + 1);
            }
            infos.clear();
            infos.addAll(tmp);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);

        root.right.right = new TreeNode(9);
        System.out.println(widthOfBinaryTree(root));
    }
}
