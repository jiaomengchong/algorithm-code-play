package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/successor-lcci/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/16 10:55
 **/
public class Problem_04Dot06_SuccessorLcci {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        List<TreeNode> ans = new ArrayList<>();
        process(root, ans);
        int index = bs(ans, p);
        return index != -1 && index + 1 < ans.size() ? ans.get(index + 1) : null;
    }

    private static int bs(List<TreeNode> ans, TreeNode target) {
        int left = 0;
        int right = ans.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (ans.get(mid).val > target.val) {
                right = mid - 1;
            } else if (ans.get(mid).val < target.val) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static void process(TreeNode root, List<TreeNode> ans) {
        if (root == null) {
            return;
        }
        process(root.left, ans);
        ans.add(root);
        process(root.right, ans);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode ret = inorderSuccessor(root, root.left);
        System.out.println(ret);
    }
}
