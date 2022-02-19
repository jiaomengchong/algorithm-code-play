package com.jmc.algorithm.jjb.class12;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/15 11:01
 */
public class Leetcode_1373 {

    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        private int min;
        private int max;
        private int subMaxSum;
        private boolean isBST;
        private int allMaxSum;

        public Info(int min, int max, int subMaxSum, boolean isBST, int allMaxSum) {
            this.min = min;
            this.max = max;
            this.subMaxSum = subMaxSum;
            this.isBST = isBST;
            this.allMaxSum = allMaxSum;
        }
    }

    public static int maxSumBST(TreeNode head) {
        if (head == null) {
            return 0;
        }

        Info info = process(head);

        return Math.max(0, info.subMaxSum);
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int min = head.val;
        int max = head.val;
        int allMaxSum = head.val;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            allMaxSum += leftInfo.allMaxSum;
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allMaxSum += rightInfo.allMaxSum;
        }

        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.max >= head.val)) {
            isBST = false;
        }

        if (rightInfo != null && (!rightInfo.isBST || rightInfo.min <= head.val)) {
            isBST = false;
        }

        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.subMaxSum;
        }

        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.subMaxSum;
        }

        int p3 = Integer.MIN_VALUE;
        if (isBST) {
            p3 = allMaxSum;
        }

        return new Info(min, max, Math.max(p1, Math.max(p2, p3)), isBST, allMaxSum);
    }


    public static void main(String[] args) {
        /*Node head = new Node(1);
        head.left = new Node(4);
        head.right = new Node(3);

        head.left.left = new Node(2);
        head.left.right = new Node(4);

        head.right.left = new Node(2);
        head.right.right = new Node(5);

        head.right.right.left = new Node(4);
        head.right.right.right = new Node(6);

        int maxSumBST = maxSumBST(head);
        System.out.println(maxSumBST);*/

        /*Node head = new Node(-4);
        head.left = new Node(-2);
        head.right = new Node(-5);
        int maxSumBST = maxSumBST(head);
        System.out.println(maxSumBST);*/

        /*Node head = new Node(4);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        int maxSumBST = maxSumBST(head);
        System.out.println(maxSumBST);*/

        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(8);
        head.left.left = new TreeNode(6);
        head.left.right = new TreeNode(1);

        head.left.left.left = new TreeNode(9);

        head.left.right.left = new TreeNode(-5);
        head.left.right.right = new TreeNode(4);

        head.left.right.left.right = new TreeNode(-3);
        head.left.right.right.right = new TreeNode(10);

        int maxSumBST = maxSumBST(head);
        System.out.println(maxSumBST);
    }
}
