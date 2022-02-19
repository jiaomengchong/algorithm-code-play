package com.jmc.algorithm.tt_leetcode.problems;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/16 17:17
 */
public class Problem01_IsBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Info {
        private int min;
        private int max;
        private boolean isBST;

        public Info(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return process(root).isBST;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int min = root.val;
        int max = root.val;
        boolean isBST = false;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(min, rightInfo.max);
        }

        boolean isLeftBST = leftInfo == null ? true : (leftInfo.isBST);
        boolean isRightBST = rightInfo == null ? true : (rightInfo.isBST);

        if (isLeftBST && isRightBST) {
            boolean leftLessHead = leftInfo == null ? true:leftInfo.max < root.val;
            boolean rightMoreHead = rightInfo == null ? true:rightInfo.min > root.val;
            if (leftLessHead && rightMoreHead) {
                isBST = true;
            }
        }

        return new Info(min, max, isBST);
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(1);
        head.right = new TreeNode(4);
        head.right.left = new TreeNode(3);
        head.right.right = new TreeNode(6);

        /*Node head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(3);*/


        /*Node head = new Node(5);
        head.left = new Node(15);
        head.left.left = new Node(1);*/

        System.out.println(isValidBST(head));

        long ttl = 60000;
        System.out.println(ttl << 3);

        Integer matchId = 0;
        System.out.println(matchId != 0);
    }
}
