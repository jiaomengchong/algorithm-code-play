package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree/
 */
public class Problem_2265_CountNodesEqualToAverageOfSubtree {
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
        private int ans;
        private int sum;
        private int nodes;

        public Info(int ans, int sum, int nodes) {
            this.ans = ans;
            this.sum = sum;
            this.nodes = nodes;
        }
    }

    public static int averageOfSubtree(TreeNode root) {
       Info info = process(root);
        return info.ans;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0, 0);
        }

        int value = root.val;
        Info left = process(root.left);
        Info right = process(root.right);

        int sum = value + left.sum + right.sum;
        int nodes = left.nodes + right.nodes + 1;
        int ans = left.ans + right.ans;
        if (sum / nodes == value) {
            ans++;
        }

        return new Info(ans, sum, nodes);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(8);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(6);

        System.out.println(averageOfSubtree(root));
    }
}
