package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/delete-node-in-a-linked-list/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/24 23:07
 **/
public class Problem_0237_DeleteNodeInALinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
