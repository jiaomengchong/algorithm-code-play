package com.jmc.algorithm.weekProblem.class_2021_11_4_week;

import java.util.ArrayList;
import java.util.List;

/**
 *             head
 *            /  \  \
 *          1    2   3
 *         /\    /\  /\
 *        4  5  6 7  8 9
 *
 * 5保留，8保留的话，变成下面的树形结构
 *             head
 *            /    \
 *           1      3
 *           \      /
 *            5    8
 * @Author jmc
 * @Description
 * @Date 2021/11/26 16:04
 **/
public class Code01_RetainTree {
    public static class Node {
        // 节点值
        private int value;
        // 是否保留
        private boolean retain;
        // 下一节点
        private List<Node> nextNodes;

        public Node(int value, boolean retain) {
            this.value = value;
            this.retain = retain;
            nextNodes = new ArrayList<>();
        }
    }

    public static Node retain(Node head) {
        if (head.nextNodes.isEmpty()) {
            return head.retain ? head : null;
        }

        List<Node> newNextNodes = new ArrayList<>();
        for (Node next : head.nextNodes) {
            Node retain = retain(next);
            if (retain != null) {
                newNextNodes.add(retain);
            }
        }

        if (!newNextNodes.isEmpty() || head.retain) {
            head.nextNodes = newNextNodes;
            return head;
        }

        return null;
    }
}
