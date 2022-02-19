package com.jmc.algorithm.jjb.class16;

import java.util.*;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/26 15:20
 */
public class Code03_TopologicalOrderDFS1 {
    public static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        private DirectedGraphNode node;
        private int depth;

        public Record(DirectedGraphNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o2.depth - o1.depth;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> recordMap = new HashMap<>();
        List<Record> records = new ArrayList<>();
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (DirectedGraphNode cur : graph) {
            records.add(f(cur, recordMap));
        }

        records.sort(new MyComparator());
        for (Record record : records) {
            ans.add(record.node);
        }

        return ans;
    }

    private Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> recordMap) {
        if (recordMap.containsKey(node)) {
            return recordMap.get(node);
        }

        int depth = 0;
        for (DirectedGraphNode next : node.neighbors) {
            depth = Math.max(depth, f(next, recordMap).depth);
        }
        Record record = new Record(node, depth + 1);
        recordMap.put(node, record);

        return record;
    }
}
