package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试链接：https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 */
public class Problem_1305_AllElementsIntwoBinarySearchTrees {
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

    List<Integer> list = new ArrayList<>();
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        inOrderTraversal(root1);
        inOrderTraversal(root2);
        list = list.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        return list;
    }

    private void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        list.add(root.val);
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2= new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        Problem_1305_AllElementsIntwoBinarySearchTrees test = new Problem_1305_AllElementsIntwoBinarySearchTrees();
        List<Integer> ans = test.getAllElements(root1, root2);
        System.out.println(ans);
    }
}
