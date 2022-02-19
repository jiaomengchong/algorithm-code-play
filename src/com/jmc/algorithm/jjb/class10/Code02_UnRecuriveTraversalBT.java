package com.jmc.algorithm.jjb.class10;

import java.util.Stack;

/**
 * 非递归方式实现二叉树的先序、中序、后序遍历
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/9 11:04
 */
public class Code02_UnRecuriveTraversalBT {
    private static class Node<V> {
        private V value;
        private Node left;
        private Node right;

        public Node(V value) {
            this.value = value;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.value);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public static void pos1(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);

        while (!stack1.isEmpty()) {
            Node pop = stack1.pop();
            stack2.push(pop);

            if (pop.left != null) {
                stack1.push(pop.left);
            }

            if (pop.right != null) {
                stack1.push(pop.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().value);
        }

    }

    public static void pos2(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            if (peek.left != null && peek.left != head && peek.right != head) {
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != head) {
                stack.push(peek.right);
            } else {
                head = peek;
                System.out.println(stack.pop().value);
            }
        }
    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack();
        Node cur = head;

        while (!stack.empty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node pop = stack.pop();
                System.out.println(pop.value);
                cur = pop.right;
            }
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
//        pre(head);
//        pos1(head);
//        System.out.println("=================");
//        pos2(head);
        in(head);
    }
}
