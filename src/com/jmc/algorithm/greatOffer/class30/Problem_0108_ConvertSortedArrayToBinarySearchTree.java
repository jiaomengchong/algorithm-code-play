package com.jmc.algorithm.greatOffer.class30;

/**
 * 将有序数组转为二叉搜索树
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/19 23:38
 */
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return process(nums, 0, nums.length - 1);
    }

    private static TreeNode process(int[] nums, int L, int R) {
        if (L > R) {
            return null;
        }

        if (L == R) {
            return new TreeNode(nums[L]);
        }

        int mid = (L + R) / 2;
        TreeNode left = process(nums, L, mid - 1);
        TreeNode right = process(nums, mid + 1, R);

        return new TreeNode(nums[mid], left, right);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode treeNode = sortedArrayToBST(nums);
        System.out.println(treeNode);
    }
}
