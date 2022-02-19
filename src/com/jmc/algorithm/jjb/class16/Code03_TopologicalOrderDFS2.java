package com.jmc.algorithm.jjb.class16;

import java.util.*;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/26 14:20
 */
public class Code03_TopologicalOrderDFS2 {
    public static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        DirectedGraphNode node;
        long record;

        public Record(DirectedGraphNode node, long record) {
            this.node = node;
            this.record = record;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o1.record == o2.record ? 0 : (o2.record > o1.record ? 1 : -1);
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        ArrayList<Record> records = new ArrayList<>();
        Map<DirectedGraphNode, Record> recordMap = new HashMap<>();
        for (DirectedGraphNode graphNode : graph) {
            records.add(f(graphNode, recordMap));
        }

        records.sort(new MyComparator());
        for (Record record : records) {
            ans.add(record.node);
        }

        return ans;
    }

    public Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> recordMap) {
        if (recordMap.containsKey(node)) {
            return recordMap.get(node);
        }

        long nodes = 0;
        for (DirectedGraphNode cur : node.neighbors) {
            nodes += f(cur, recordMap).record;
        }
        Record record = new Record(node, nodes + 1);
        recordMap.put(node, record);

        return record;
    }
}
