package com.jmc.algorithm.dailyChallenge;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/design-an-ordered-stream/
 */
public class Problem_1656_DesignAOorderedStream {
    static class OrderedStream {
        public int ptr;
        public int size;
        public Map<Integer, String> map;

        public OrderedStream(int n) {
            size = n;
            map = new HashMap<>();
            ptr = 1;
        }

        public List<String> insert(int idKey, String value) {
            List<String> ans = new ArrayList<>();
            map.put(idKey, value);
            for (int i = ptr; i <= size; i++) {
                if (map.containsKey(i)) {
                    ans.add(map.get(i));
                } else {
                    break;
                }
            }
            if (ptr == idKey) {
                while (map.containsKey(ptr)) {
                    ptr++;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        //输入
        //["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
        //[[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
        //输出
        //[null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]

        OrderedStream os = new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc"));
        System.out.println(os.insert(1, "aaaaa"));
        System.out.println(os.insert(2, "bbbbb"));
        System.out.println(os.insert(5, "eeeee"));
        System.out.println(os.insert(4, "ddddd"));
        System.out.println("test end!");
    }
}
