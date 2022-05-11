package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * @Author jmc
 * @Description
 * @Date 2022/5/11 9:44
 **/
public class Problem_0449_SerializeAndDeserializeBst {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<String> ans = new ArrayList<>();
        preSerialize(root, ans);
        String ret = ans.toString();
        return ret.substring(1, ret.length() - 1);
    }

    public static List<String> preSerialize(TreeNode root, List<String> ans) {
        if (root == null) {
            return ans;
        }
        ans.add(String.valueOf(root.val));
        preSerialize(root.left, ans);
        preSerialize(root.right, ans);

        return ans;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] str = data.split(",");
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < str.length; i++) {
            queue.offer(Integer.valueOf(str[i].trim()));
        }
        TreeNode head = preDeserialize(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return head;
    }

    private static TreeNode preDeserialize(Queue<Integer> queue, int low, int upper) {
        if (queue.isEmpty() || queue.peek() < low || queue.peek() > upper) {
            return null;
        }

        Integer val = queue.poll();
        TreeNode root = new TreeNode(val);
        root.left = preDeserialize(queue, low, val);
        root.right = preDeserialize(queue, val, upper);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        String serialize = serialize(root);
        System.out.println(serialize);

        TreeNode treeNode = deserialize(serialize);
        System.out.println(treeNode);
    }
}
