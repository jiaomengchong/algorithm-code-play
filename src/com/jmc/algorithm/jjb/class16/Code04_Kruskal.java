package com.jmc.algorithm.jjb.class16;

import java.util.*;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/26 16:17
 */
public class Code04_Kruskal {
    private static class UnionFind {
        private Map<Node, Node> parents;
        private Map<Node, Integer> sizeMap;

        public UnionFind(Collection<Node> nodes) {
            this.parents = new HashMap<>();
            this.sizeMap = new HashMap<>();

            for (Node node : nodes) {
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node cur) {
            Stack<Node> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }

            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }

            return cur;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            Node aHead = findFather(a);
            Node bHead = findFather(b);
            if (aHead != bHead) {
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                Node big = aSize >= bSize ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }
    }

    private static class MyComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public Set<Edge> kruskal(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new MyComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                ans.add(edge);
            }
        }

        return ans;
    }
}
