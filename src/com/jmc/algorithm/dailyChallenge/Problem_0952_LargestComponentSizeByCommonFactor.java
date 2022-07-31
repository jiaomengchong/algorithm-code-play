package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/largest-component-size-by-common-factor/
 */
public class Problem_0952_LargestComponentSizeByCommonFactor {
    public static int largestComponentSize(int[] nums) {
        int N = nums.length, ans = 0, max = Integer.MIN_VALUE;
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            max = Math.max(max, nums[i]);
            numList.add(nums[i]);
        }

        UnionSet unionSet = new UnionSet(numList);
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    if (!unionSet.nodes.containsKey(i)) {
                        Node node = new Node(i);
                        unionSet.nodes.put(i, node);
                        unionSet.parents.put(node, node);
                        unionSet.sizeMap.put(node, 1);
                    }
                    unionSet.union(i, num);
                    if (!unionSet.nodes.containsKey(num / i)) {
                        Node node = new Node(num / i);
                        unionSet.nodes.put(num / i, node);
                        unionSet.parents.put(node, node);
                        unionSet.sizeMap.put(node, 1);
                    }
                    unionSet.union(num, num / i);
                }
            }
        }

        for (int num : nums) {
            Integer value = (Integer) unionSet.sizeMap.getOrDefault(unionSet.nodes.get(num), 0);
            ans = Math.max(ans, value);
        }
        return ans > N ? N : ans;
    }

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

            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
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
                Integer aSize = sizeMap.getOrDefault(aHead, 0);
                Integer bSize = sizeMap.getOrDefault(bHead, 0);
                if (aSize == 0 || bSize == 0) {
                    return;
                }
                Node<V> big = aSize > bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,6,15,35};
        System.out.println(largestComponentSize(nums));
    }
}
