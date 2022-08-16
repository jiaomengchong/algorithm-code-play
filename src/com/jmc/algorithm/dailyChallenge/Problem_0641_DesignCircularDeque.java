package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/design-circular-deque/
 */
public class Problem_0641_DesignCircularDeque {
    class MyCircularDeque {

        public class Node {
            public int value;
            public Node last;
            public Node next;

            public Node(int value) {
                this.value = value;
            }
        }

        public Node head;
        public Node tail;
        public int capacity;
        public int size;

        public MyCircularDeque(int k) {
            capacity = k;
            size = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            Node cur = new Node(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            Node cur = new Node(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            Node cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            Node cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = cur.last;
                cur.last = null;
                tail.next = null;
            }
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return head.value;
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return tail.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }
}
