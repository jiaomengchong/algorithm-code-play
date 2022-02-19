package com.jmc.algorithm.jjb.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现二叉树的按层遍历
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/9 15:55
 */
public class Code01_LevelTraversalBT {

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void level(Node head) {
        if (head == null) {
            return;
        }

        Queue<Node> queue = new LinkedList();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            if (poll.left != null) {
                queue.add(poll.left);
            }

            if (poll.right != null) {
                queue.add(poll.right);
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
        level(head);
    }
}
