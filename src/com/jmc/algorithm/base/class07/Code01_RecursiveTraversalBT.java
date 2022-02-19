package com.jmc.algorithm.base.class07;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/11/24 18:26
 */
public class Code01_RecursiveTraversalBT {

    public static class Node {
        Node left;
        Node right;
        String value;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }

        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void post(Node head){
        if (head == null) {
            return;
        }

        post(head.left);
        post(head.right);
        System.out.println(head.value);
    }

    public static void in (Node head){
        if (head == null) {
            return;
        }

        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    public static void main(String[] args) {
        Node head = new Node("1");
        head.left = new Node("2");
        head.right = new Node("3");
        head.left.left = new Node("4");
        head.left.right = new Node("5");
        head.right.left = new Node("6");
        head.right.right = new Node("7");

        pre(head);
        System.out.println("===============");
        post(head);
        System.out.println("===============");
        in(head);
        System.out.println("===============");

    }
}
