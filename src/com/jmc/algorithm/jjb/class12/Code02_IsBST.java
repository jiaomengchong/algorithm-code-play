package com.jmc.algorithm.jjb.class12;


import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是二叉搜索树
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/11 15:09
 */
public class Code02_IsBST {

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        private boolean isBST;
        private int minValue;
        private int maxValue;

        public Info(boolean isBST, int minValue, int maxValue) {
            this.isBST = isBST;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }

        Info info = process(head);


        return info.isBST;
    }

    public static Info process(Node head) {

        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isBST = false;
        int minValue = head.value;
        int maxValue = head.value;

        if (leftInfo != null) {
            minValue = Math.min(minValue, leftInfo.minValue);
            maxValue = Math.max(maxValue, leftInfo.maxValue);
        }

        if (rightInfo != null) {
            minValue = Math.min(minValue, rightInfo.minValue);
            maxValue = Math.max(maxValue, rightInfo.maxValue);
        }

        if ((leftInfo == null ? true : (leftInfo.isBST && leftInfo.maxValue < head.value)) &&
                (rightInfo == null ? true : (rightInfo.isBST && rightInfo.minValue > head.value))) {
            isBST = true;
        }


        return new Info(isBST, minValue, maxValue);
    }

    public static boolean isBST3(Node head) {
        if (head == null) {
            return true;
        }
        boolean ans = true;
        Node cur = head;
        Node mostRight;
        Node pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre.value >= cur.value) {
                ans = false;
            }
            pre = cur;
            cur = cur.right;
        }

        return ans;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {

        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST3(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
