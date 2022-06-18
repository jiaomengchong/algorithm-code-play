package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/4ueAj6/
 */
public class OfferII_0029_SortCircularLinkedList {
    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public static Node insert(Node head, int insertVal) {
        if (head == null) {
            Node res = new Node(insertVal);
            res.next = res;
            return res;
        }

        Node cur = head;
        Node last = null;
        boolean first = true;
        while (first) {
            if (insertVal > cur.val && insertVal <= cur.next.val) {
                Node next = cur.next;
                cur.next = new Node(insertVal);
                cur.next.next = next;
                return head;
            }
            if (insertVal < cur.val && cur.val > cur.next.val && insertVal <= cur.next.val) {
                Node next = cur.next;
                cur.next = new Node(insertVal);
                cur.next.next = next;
                return head;
            }
            if (insertVal > cur.val && cur.val > cur.next.val && insertVal >= cur.next.val) {
                Node next = cur.next;
                cur.next = new Node(insertVal);
                cur.next.next = next;
                return head;
            }
            last = cur;
            cur = cur.next;
            first = cur != head;
        }

        last.next = new Node(insertVal);
        last.next.next = head;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = head;
        Node ans = insert(head, 0);
        System.out.println(ans);
    }
}
