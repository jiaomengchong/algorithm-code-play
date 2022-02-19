package com.jmc.algorithm.greatOffer.class14;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 恢复二叉搜索树
 * 测试链接：https://leetcode-cn.com/problems/recover-binary-search-tree/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/23 14:35
 */
public class Code05_RecoverBinarySearchTree {
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

    public void recoverTree(TreeNode root) {
        // TODO 用morris遍历实现
        TreeNode[] errorNodes = getTwoErrorNodes(root);
        int temp = errorNodes[0].val;
        errorNodes[0].val = errorNodes[1].val;
        errorNodes[1].val = temp;
    }

    private static TreeNode[] getTwoErrorNodes(TreeNode root) {
        TreeNode[] ans = new TreeNode[2];
        Queue<TreeNode> queue = inSerial(root);
        TreeNode pre = null;
        TreeNode cur = null;

        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur != null && pre != null && cur.val < pre.val) {
                ans[0] = ans[0] != null ? ans[0] : pre;
                ans[1] = cur;
            }
            pre = cur;
        }
        return ans;
    }

    private static Queue<TreeNode> inSerial(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ins(root, queue);
        return queue;
    }

    private static void ins(TreeNode root, Queue<TreeNode> queue) {
        if (root != null) {
            ins(root.left, queue);
            queue.add(root);
            ins(root.right, queue);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(2);
        TreeNode[] twoErrorNodes = getTwoErrorNodes(root);
        for (TreeNode node : twoErrorNodes) {
            System.out.println(node.val);
        }
    }

}
