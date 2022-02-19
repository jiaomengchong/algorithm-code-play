package com.jmc.algorithm.greatOffer.class30;

import java.util.LinkedList;

/**
 * 填充每个节点的下一个右侧节点指针
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/20 14:47
 */
public class Problem_0116_PopulatingNextRightPointersInEachNode {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static class MyQueue {
        private int size;
        private Node head;
        private Node tail;

        public MyQueue() {
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(Node node) {
            size++;
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        public Node poll() {
            size--;
            Node ans = head;
            head = ans.next;
            ans.next = null;
            return ans;
        }

    }

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.pollFirst();
                if (cur.left != null) {
                    queue.offerLast(cur.left);
                }
                if (cur.right != null) {
                    queue.offerLast(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }

        return root;
    }

    public static Node connect1(Node root) {
        if (root == null) {
            return null;
        }

        MyQueue queue = new MyQueue();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size;
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println(connect(root));
    }
}
