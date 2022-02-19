package com.jmc.algorithm.jjb.class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/27 12:43
 */
public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectedNode = new HashSet<>();
        distanceMap.put(from, 0);
        Node minNode = getMinDistanceAndNoSelectedNode(distanceMap, selectedNode);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                if (!distanceMap.containsKey(edge.to)) {
                    distanceMap.put(edge.to, edge.weight + distance);
                } else {
                    distanceMap.put(edge.to, Math.min(distanceMap.get(edge.to), edge.weight + distance));
                }
            }
            selectedNode.add(minNode);
            minNode = getMinDistanceAndNoSelectedNode(distanceMap, selectedNode);
        }

        return distanceMap;
    }

    private static Node getMinDistanceAndNoSelectedNode(HashMap<Node, Integer> distanceMap, Set<Node> selectedNode) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!selectedNode.contains(entry.getKey()) && minDistance > entry.getValue()) {
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }

        return minNode;
    }

    public static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public NodeRecord pop() {
            NodeRecord record = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return record;
        }

        public void heapInsert(int index) {
            while (distanceMap.get(nodes[(index - 1) / 2]) > distanceMap.get(nodes[index])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 > size ? left : (distanceMap.get(nodes[left]) >= distanceMap.get(nodes[left + 1]) ? left + 1 : left);
                smallest = distanceMap.get(nodes[smallest]) >= distanceMap.get(nodes[index]) ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void swap(int i, int j) {
            heapIndexMap.put(nodes[i], j);
            heapIndexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;

        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(size++);
            }
        }

        public boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        public boolean inHeap(Node node) {
            return isEntered(node) && distanceMap.get(node) != -1;
        }
    }

    public static class NodeRecord {
        private Node node;
        private int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public HashMap<Node, Integer> dijkstra2(Node head, int size) {
        HashMap<Node, Integer> ans = new HashMap<>();
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            ans.put(cur, distance);
        }
        return ans;
    }
}
