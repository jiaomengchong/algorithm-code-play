package com.jmc.algorithm.greatOffer.class14;

/**
 * 给定一棵完全二叉树，返回这棵树的节点个数，要求时间复杂度小于O(n)
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/21 20:34
 */
public class Code04_CompleteTreeNodeNumber {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int nodeNums(Node head) {
        if (head == null) {
            return 0;
        }

        int h = mostLeftLevel(head, 1);
        return bs(head, 1, h);
    }

    private static int bs(Node node, int level, int h) {
        if (level == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, level + 1) == h) {
            // 左树是满二叉树
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        } else {
            // 右树是满二叉树，并且高度少1
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }

    private static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNums(head));
    }
}
