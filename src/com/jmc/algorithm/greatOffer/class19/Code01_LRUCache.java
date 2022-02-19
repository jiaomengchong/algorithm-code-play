package com.jmc.algorithm.greatOffer.class19;

import java.util.HashMap;

/**
 * LRU缓存机制
 * 测试链接：https://leetcode-cn.com/problems/lru-cache/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/30 10:28
 */
public class Code01_LRUCache {
    private MyCache cache;

    public Code01_LRUCache(int capacity) {
        cache = new MyCache<Integer, Integer>(capacity);
    }

    public int get(int key) {
        Object ans = cache.get(key);
        return ans == null ? -1 : 1;
    }

    public void put(int key, int value) {
        cache.set(key, value);
    }

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> last;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class DoubleLinkedQueue<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public DoubleLinkedQueue() {
            head = null;
            tail = null;
        }

        public void add(Node<K, V> node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public Node<K,V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = res.next;
                head.last = null;
                res.next = null;
            }
            return res;
        }

        // head -> a -> b -> tail
        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }
            if (head == node) {
                head = node.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            tail.next = node;
            node.last = tail;
            tail = node;
        }
    }

    public static class MyCache<K, V> {
        private HashMap<K, Node<K, V>> keyHashMap;
        private DoubleLinkedQueue<K, V> nodeList;
        private final int capacity;

        public MyCache(int capacity) {
            this.capacity = capacity;
            keyHashMap = new HashMap<>();
            nodeList = new DoubleLinkedQueue<>();
        }

        public void set(K key, V value) {
            if (keyHashMap.containsKey(key)) {
                Node<K, V> node = keyHashMap.get(key);
                node.value = value;
                // 移动node至tail
                nodeList.moveNodeToTail(node);
            } else {
                Node<K, V> node = new Node<>(key, value);
                keyHashMap.put(key, node);
                nodeList.add(node);
                if (keyHashMap.size() == capacity + 1) {
                    // 移除head
                    moveUnusedNode();
                }
            }
        }

        public V get(K key) {
            if (keyHashMap.containsKey(key)) {
                Node<K, V> node = keyHashMap.get(key);
                // 将节点移动至tail
                nodeList.moveNodeToTail(node);
                return node.value;
            } else {
                return null;
            }
        }

        private void moveUnusedNode() {
            Node<K, V> head = nodeList.removeHead();
            keyHashMap.remove(head.key);
        }
    }
}
