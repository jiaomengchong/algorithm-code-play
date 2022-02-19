package com.jmc.algorithm.base.class06;

import java.util.Stack;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/2 13:39
 */
public class Code02_IsPalindromeList {
    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 利用栈判断是否是回文
     *
     * @param head
     * @return
     */
    public static boolean process1(Node head) {
        if (head == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        Node step = head;
        while (!stack.isEmpty() && step != null) {
            Node pop = stack.pop();
            if (pop.value != step.value) {
                return false;
            }
            step = step.next;
        }

        return true;
    }

    /**
     * 改原链表实现判断是否回文
     *
     * @param head
     * @return
     */
    public static boolean process2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        if (head.next.next == null) {
            return head == head.next ? true : false;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node tail = fast.next == null ? fast : fast.next;


        return true;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(1);
        node1.next.next = new Node(2);
        node1.next.next.next = new Node(1);
        System.out.println(process1(node1));
    }
}
