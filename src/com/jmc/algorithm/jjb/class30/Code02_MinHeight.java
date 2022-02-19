package com.jmc.algorithm.jjb.class30;

/**
 * 给定一棵二叉树的头节点head
 * 求以head为头的树中，最小深度是多少？
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/25 16:21
 */
public class Code02_MinHeight {
    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class Info {
        private int height;

        public Info(int height) {
            this.height = height;
        }
    }

    public static int minHeightDp(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head).height;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }

        int height = Integer.MAX_VALUE;
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        if (leftInfo != null) {
            height = Math.min(leftInfo.height, height);
        }
        if (rightInfo != null) {
            height = Math.min(rightInfo.height, height);
        }

        return new Info(1 + (height == Integer.MAX_VALUE ? 0 : height));
    }

    public static int minHeightMorris(Node head) {
        if (head == null) {
            return 0;
        }

        Node cur = head;
        Node mostRight;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            int rightBoardSize = 1;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            } else {
                curLevel++;
            }
            cur = cur.right;
        }

        cur = head;
        int finalRight = 1;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null && cur.right == null) {
            minHeight = Math.min(minHeight, finalRight);
        }

        return minHeight;
    }

    public static void main(String[] args) {
        Node head = new Node(4);

        System.out.println(minHeightDp(head));
        System.out.println(minHeightMorris(head));
    }
}
