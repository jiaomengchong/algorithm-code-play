package com.jmc.algorithm.jjb.class10;

/**
 * 递归方式实现二叉树的先序、中序、后序遍历
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/9 10:25
 */
public class Code01_RecuriveTraversalBT {

    private static class Node<V> {
        private V value;
        private Node left;
        private Node right;

        public Node(V value) {
            this.value = value;
        }
    }

    public static void func(Node head) {
        if (head == null) {
            return;
        }

        func(head.left);
        func(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left=new Node(2);
        head.right=new Node(3);
        head.left.left=new Node(4);
        head.left.right=new Node(5);
        head.right.left=new Node(6);
        head.right.right=new Node(7);

        func(head);
    }
}
