package com.jmc.algorithm.greatOffer.class02;

import java.util.HashMap;
import java.util.Map;

/**
 * 已知一个消息流会不断地吐出整数 1~N，
 * 但不一定按照顺序依次吐出
 * 如果上次打印的序号为i， 那么当i+1出现时
 * 请打印 i+1 及其之后接收过的并且连续的所有数
 * 直到1~N全部接收并打印完
 * 请设计这种接收并打印的结构
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/27 19:26
 */
public class Code03_ReceiveAndPrintOrderLine {
    public static class Node {
        private String info;
        private Node next;

        public Node(String info) {
            this.info = info;
        }
    }

    public static class MessageBox {
        private Map<Integer, Node> headMap;
        private Map<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node node = new Node(info);
            headMap.put(num, node);
            tailMap.put(num, node);
            if (tailMap.containsKey(num - 1)) {
                Node pre = tailMap.get(num - 1);
                pre.next = node;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if (headMap.containsKey(num + 1)) {
                node.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }
            if (waitPoint == num) {
                print();
            }
        }

        public void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MessageBox box = new MessageBox();
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11
    }
}
