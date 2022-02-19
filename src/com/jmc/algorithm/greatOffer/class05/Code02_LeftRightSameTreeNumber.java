package com.jmc.algorithm.greatOffer.class05;

/**
 * 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的子树是相等子树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/8 17:22
 */
public class Code02_LeftRightSameTreeNumber {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int sameSubTree1(Node head) {
        if (head == null) {
            return 0;
        }

        return sameSubTree1(head.left) + sameSubTree1(head.right) + (isSame(head.left, head.right) ? 1 : 0);
    }

    private static boolean isSame(Node head1, Node head2) {
        if (head1 == null ^ head2 == null) {
            return false;
        }

        if (head1 == null && head2 == null) {
            return true;
        }

        // 2个都不为空
        return head1.value == head2.value && isSame(head1.left, head2.left) && isSame(head1.right, head2.right);
    }

    public static int sameSubTree2(Node head) {
        if (head == null) {
            return 0;
        }
        Hash hash = new Hash("SHA-256");
        return process2(head, hash).ans;
    }

    private static Info process2(Node head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }

        Info left = process2(head.left, hash);
        Info right = process2(head.right, hash);
        int ans = left.ans + right.ans + (left.str.equals(right.str) ? 1 : 0);
        String str = hash.hashCode(head.value + "," + left.str + right.str);
        return new Info(ans, str);
    }

    public static class Info {
        private int ans;
        private String str;

        public Info(int ans, String str) {
            this.ans = ans;
            this.str = str;
        }
    }

    // for test
    private static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, (int) (Math.random() * maxValue));
            head.right = randomBinaryTree(restLevel - 1, (int) (Math.random() * maxValue));
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始:");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameSubTree1(head);
            int ans2 = sameSubTree2(head);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

}
