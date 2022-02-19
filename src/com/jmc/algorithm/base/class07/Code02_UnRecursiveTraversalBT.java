package com.jmc.algorithm.base.class07;

import java.util.Stack;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/11/24 18:59
 */
public class Code02_UnRecursiveTraversalBT {

    private static class Node {
        Node left;
        Node right;
        String value;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void pre(Node head) {
        System.out.println("pre-order");
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void in(Node head) {
        System.out.println("in-order");
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                Node node = stack.pop();
                System.out.println(node.value);
                head = node.right;
            }
        }

    }

    public static void post(Node head) {
        System.out.println("post-order");
        if (head != null) {
            Stack<Node> s1 = new Stack();
            Stack<Node> s2 = new Stack();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);

                if (head.left != null) {
                    s1.push(head.left);
                }

                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value);
            }
        }
    }

    public static void post2(Node head) {
        System.out.println("post2-order");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Node c = null;
            stack.push(head);
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && c.left != head && c.right != head) {
                    stack.push(c.left);
                } else if (c.right != null && c.right != head) {
                    stack.push(c.right);
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                }
            }
        }
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
        System.out.println("==================");
        in(head);
        System.out.println("==================");
        post(head);
        System.out.println("==================");
        post2(head);
    }
}
