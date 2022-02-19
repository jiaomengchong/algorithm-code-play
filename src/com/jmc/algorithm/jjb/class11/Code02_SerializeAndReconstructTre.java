package com.jmc.algorithm.jjb.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/9 16:27
 */
public class Code02_SerializeAndReconstructTre {
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();

        pres(head, ans);

        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    public static Node buildByPreQueue(Queue<String> queueList) {
        if (queueList == null || queueList.size() == 0) {
            return null;
        }

        return preb(queueList);
    }

    private static Node preb(Queue<String> queueList) {
        String poll = queueList.poll();
        if (poll == null) {
            return null;
        }

        Node head = new Node(Integer.valueOf(poll));
        head.left = preb(queueList);
        head.right = preb(queueList);

        return head;
    }

    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    public static void poss(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPosQueue(Queue<String> queueList) {
        if (queueList == null || queueList.size() == 0) {
            return null;
        }

        Stack<String> stack = new Stack<>();
        while (!queueList.isEmpty()) {
            stack.push(queueList.poll());
        }

        return posb(stack);
    }

    public static Node posb(Stack<String> stack) {
        String pop = stack.pop();
        if (pop == null) {
            return null;
        }

        Node head = new Node(Integer.valueOf(pop));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }

        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();

        if (head == null) {
            ans.add(null);
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                ans.add(String.valueOf(cur.value));
                if (cur.left != null) {
                    ans.add(String.valueOf(cur.left.value));
                    queue.add(cur.left);
                } else {
                    ans.add(null);
                }

                if (cur.right != null) {
                    ans.add(String.valueOf(cur.right.value));
                    queue.add(cur.right);
                } else {
                    ans.add(null);
                }
            }
        }

        return ans;
    }

    public static void pos(Node head) {
        if (head == null) {
            return;
        }

        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Queue<String> preSerial = preSerial(head);
        System.out.println(preSerial.toString());

        Node node = buildByPreQueue(preSerial);
        pre(node);

        System.out.println("=============================");

        Queue<String> posSerial = posSerial(head);
        System.out.println(posSerial.toString());
        Node node1 = buildByPosQueue(posSerial);
        pos(node1);

        System.out.println("===============================");
        Queue<String> levelSerial = levelSerial(head);
        System.out.println(levelSerial.toString());
    }
}
