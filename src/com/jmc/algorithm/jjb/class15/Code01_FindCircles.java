package com.jmc.algorithm.jjb.class15;

import java.util.*;

/**
 * 朋友圈
 * <p>
 * https://leetcode-cn.com/problems/friend-circles/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/23 16:51
 */
public class Code01_FindCircles {

    public static class Node<V> {
        private V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            if (values != null && values.size() != 0) {
                for (V value : values) {
                    Node<V> node = new Node<>(value);
                    nodes.put(value, node);
                    parents.put(node, node);
                    sizeMap.put(node, 1);
                }
            }
        }

        public Node<V> findFather(Node<V> a) {
            Stack<Node<V>> path = new Stack<>();
            Node<V> cur = a;
            while (parents.get(cur) != cur) {
                path.push(cur);
                cur = parents.get(cur);
            }

            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }

            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                Integer aSize = sizeMap.get(aHead);
                Integer bSize = sizeMap.get(bHead);
                Node<V> big = aSize > bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }
    }

    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }

        int size = M.length;
        List<Friend> friends = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (M[i][j] == 1) {
                    set.add(i);
                    set.add(j);
                    friends.add(new Friend(i, j));
                }
            }
        }
        UnionSet<Integer> unionSet = new UnionSet<>(new ArrayList<>(set));
        for (int i = 0; i < friends.size(); i++) {
            Friend friend = friends.get(i);
            unionSet.union(friend.a, friend.b);
        }

        return unionSet.sizeMap.size();
    }

    public static class Friend {
        private int a;
        private int b;

        public Friend(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static int findCircleNum2(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }

        int N = M.length;
        UnionSetArray unionSet = new UnionSetArray(N);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (M[i][j] == 1) {
                    unionSet.union(i,j);
                }
            }
        }

        return unionSet.sets();

    }

    public static class UnionSetArray {
        private int[] parents;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionSetArray(int N) {
            parents = new int[N];
            help = new int[N];
            size = new int[N];
            sets = N;

            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }

            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }

            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    parents[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    parents[f1] = f2;
                    size[f2] += size[f1];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

    public static void main(String[] args) {
        int[][] M = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}};

        System.out.println(findCircleNum(M));
        System.out.println(findCircleNum2(M));
    }


}
