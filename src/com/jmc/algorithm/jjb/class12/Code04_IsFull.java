package com.jmc.algorithm.jjb.class12;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/11 14:09
 */
public class Code04_IsFull {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        private boolean isFull;
        private int height;
        private int size;

        public Info(boolean isFull, int height, int size) {
            this.isFull = isFull;
            this.height = height;
            this.size = size;
        }
    }

    public static boolean isFull(Node head) {
        if (null == head) {
            return true;
        }

        return process(head).isFull;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isFull;
        int height = 1;
        int size = 1;

        height += Math.max(leftInfo.height, rightInfo.height);
        size += leftInfo.size + rightInfo.size;
        isFull = ((1 << height) - 1) == size ? true : false;

        return new Info(isFull, height, size);

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        System.out.println(isFull(head));
    }
}
