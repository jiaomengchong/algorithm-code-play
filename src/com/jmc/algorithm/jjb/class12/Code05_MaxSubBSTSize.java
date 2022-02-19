package com.jmc.algorithm.jjb.class12;

import java.util.ArrayList;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/14 13:39
 */
public class Code05_MaxSubBSTSize {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        private int min;
        private int max;
        private int maxSubBSTSize;
        private int maxSize;

        public Info(int min, int max, int maxSubBSTSize, int maxSize) {
            this.min = min;
            this.max = max;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxSize = maxSize;
        }
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head).maxSubBSTSize;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int min = head.value;
        int max = head.value;
        int maxSize = 1;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSize += leftInfo.maxSize;
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            maxSize += rightInfo.maxSize;
        }

        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxSubBSTSize;
        }

        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxSubBSTSize;
        }

        boolean isLeftBST = leftInfo == null ? true : (leftInfo.maxSize == leftInfo.maxSubBSTSize);
        boolean isRightBST = rightInfo == null ? true : (rightInfo.maxSize == rightInfo.maxSubBSTSize);
        int p3 = -1;
        if (isLeftBST && isRightBST) {
            boolean leftMaxLessHead = leftInfo == null ? true : (leftInfo.max < head.value);
            boolean rightMinMoreHead = rightInfo == null ? true : (rightInfo.min > head.value);
            if (leftMaxLessHead && rightMinMoreHead) {
                int leftMaxSubBSTSize = leftInfo == null ? 0 : leftInfo.maxSubBSTSize;
                int rightMaxSubBSTSize = rightInfo == null ? 0 : rightInfo.maxSubBSTSize;
                p3 = leftMaxSubBSTSize + rightMaxSubBSTSize + 1;
            }
        }
        return new Info(min, max, Math.max(p1, Math.max(p2, p3)), maxSize);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                printTree(head);
            }
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
