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

    public List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list12 = new ArrayList<>();
        inOrder(root1, list1);
        inOrder(root2, list12);

        // 合并
        List<Integer> ans = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (true) {
            if (list1.size() == index1) {
                ans.addAll(list12.subList(index2, list12.size()));
                break;
            }
            if (list12.size() == index2) {
                ans.addAll(list1.subList(index1, list1.size()));
                break;
            }
            if (list1.get(index1) < list12.get(index2)) {
                ans.add(list1.get(index1++));
            } else {
                ans.add(list12.get(index2++));
            }
        }

        return ans;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
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

        List<Integer> ans1 = test.getAllElements1(root1, root2);
        System.out.println(ans1);
    }
}
