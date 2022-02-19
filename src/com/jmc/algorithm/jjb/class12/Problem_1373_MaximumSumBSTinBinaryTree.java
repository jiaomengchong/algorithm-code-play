package com.jmc.algorithm.jjb.class12;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/15 13:40
 */
public class Problem_1373_MaximumSumBSTinBinaryTree {
    // 不用提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 提交下面的方法
    public static int maxSumBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(0, process(root).subBSTMaxSum);
    }

    public static class Info {
        public int subBSTMaxSum;
        public int allSum;
        public boolean isBST;
        public int min;
        public int max;

        public Info(int s, int a, boolean i, int mi, int ma) {
            subBSTMaxSum = s;
            allSum = a;
            isBST = i;
            min = mi;
            max = ma;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val;
        int max = x.val;
        int allSum = x.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            allSum += leftInfo.allSum;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allSum += rightInfo.allSum;
        }
        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.max >= x.val)) {
            isBST = false;
        }
        if (rightInfo != null && (!rightInfo.isBST || rightInfo.min <= x.val)) {
            isBST = false;
        }
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.subBSTMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.subBSTMaxSum;
        }
        int p3 = Integer.MIN_VALUE;
        if (isBST) {
            p3 = allSum;
        }
        int subBSTMaxSum = Math.max(Math.max(p1, p2), p3);
        return new Info(subBSTMaxSum, allSum, isBST, min, max);
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
        head.left.left= new TreeNode(6);
        head.left.right= new TreeNode(1);

        head.left.left.left= new TreeNode(9);

        head.left.right.left= new TreeNode(-5);
        head.left.right.right= new TreeNode(4);

        head.left.right.left.right= new TreeNode(-3);
        head.left.right.right.right= new TreeNode(10);

        int maxSumBST = maxSumBST(head);
        System.out.println(maxSumBST);
    }
}
