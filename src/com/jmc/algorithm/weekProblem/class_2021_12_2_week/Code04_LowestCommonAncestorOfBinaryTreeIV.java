package com.jmc.algorithm.weekProblem.class_2021_12_2_week;

import java.util.TreeSet;

/**
 * 测试链接 : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/14 22:51
 **/
public class Code04_LowestCommonAncestorOfBinaryTreeIV {
    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static class Info {
        private TreeNode find;
        private int removes;

        public Info(TreeNode find, int removes) {
            this.find = find;
            this.removes = removes;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode[] nodes) {
        TreeSet<Integer> treeSet = new TreeSet();
        for (TreeNode node : nodes) {
            treeSet.add(node.value);
        }

        return process(head, treeSet, treeSet.size()).find;
    }

    private static Info process(TreeNode head, TreeSet<Integer> treeSet, int size) {
        if (head == null) {
            return new Info(null, 0);
        }

        Info left = process(head.left, treeSet, size);
        if (left.find != null) {
            return left;
        }

        Info right = process(head.right, treeSet, size);
        if (right.find != null) {
            return right;
        }

        int cur = treeSet.contains(head.value) ? 1 : 0;
        treeSet.remove(head.value);
        if (left.removes + right.removes + cur == size) {
                    return new Info(head, size);
        } else {
            return new Info(null, left.removes + right.removes);
        }
    }

    public static void main(String[] args) {
        //输入: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8]
        //输出: 3
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(5);
        head.left.left = new TreeNode(6);
        head.left.right = new TreeNode(2);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(4);


        head.right = new TreeNode(1);
        head.right.left = new TreeNode(0);
        head.right.right = new TreeNode(8);

        TreeNode[] treeNodes = new TreeNode[9];
        treeNodes[0] = head.right.left;
        treeNodes[1] = head.right;
        treeNodes[2] = head.left.right;
        treeNodes[3] = head;
        treeNodes[4] = head.left.right.right;
        treeNodes[5] = head.left;
        treeNodes[6] = head.left.left;
        treeNodes[7] = head.left.right.left;
        treeNodes[8] = head.right.right;

        TreeNode ans = lowestCommonAncestor(head, treeNodes);
        System.out.println(ans.value);
    }
}
