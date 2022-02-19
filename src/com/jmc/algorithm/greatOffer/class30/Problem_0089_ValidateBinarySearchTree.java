package com.jmc.algorithm.greatOffer.class30;

/**
 * 验证二叉搜索树
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/13 19:20
 */
public class Problem_0089_ValidateBinarySearchTree {
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
        private int minValue;
        private int maxValue;
        private boolean isBST;

        public Info(int minValue, int maxValue, boolean isBST) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.isBST = isBST;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return dfs(root).isBST;
    }

    private static Info dfs(TreeNode head) {
        if (head == null) {
            return null;
        }

        Info leftInfo = dfs(head.left);
        Info rightInfo = dfs(head.right);

        int minValue = head.val;
        int maxValue = head.val;
        if (leftInfo != null) {
            minValue = Math.min(minValue, leftInfo.minValue);
            maxValue = Math.max(maxValue, leftInfo.maxValue);
        }
        if (rightInfo != null) {
            minValue = Math.min(minValue, rightInfo.minValue);
            maxValue = Math.max(maxValue, rightInfo.maxValue);
        }

        boolean isBST = false;
        boolean leftIsBST = leftInfo == null ? true : leftInfo.isBST;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.isBST;

        if (leftIsBST && rightIsBST) {
            boolean leftLessHead = leftInfo == null ? true : leftInfo.maxValue < head.val;
            boolean rightMoreHead = rightInfo == null ? true : rightInfo.minValue > head.val;
            isBST = leftLessHead && rightMoreHead;
        }

        return new Info(minValue, maxValue, isBST);
    }

    public static boolean isValidBST1(TreeNode head) {
        if (head == null) {
            return true;
        }

        TreeNode cur = head;
        TreeNode mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    System.out.print(cur.val + " ");
                    if (pre != null && pre >= cur.val) {
                        ans = false;
                    }
                    pre = cur.val;
                    cur = cur.right;
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + " ");
                if (pre != null && pre >= cur.val) {
                    ans = false;
                }
                pre = cur.val;
                cur = cur.right;
            }
        }
        System.out.println();
        return ans;
    }

    public static void main(String[] args) {
        // [2147483647,2147483647]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left= new TreeNode(1);
        root.left.right= new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(isValidBST1(root));
        System.out.println(isValidBST(root));
        System.out.println(Integer.MAX_VALUE);
    }
}
