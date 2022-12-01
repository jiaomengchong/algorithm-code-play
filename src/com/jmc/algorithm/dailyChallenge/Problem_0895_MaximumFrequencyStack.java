package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-frequency-stack/
 */
public class Problem_0895_MaximumFrequencyStack {
    static class FreqStack {
        Map<Integer, Integer> cntMap = new HashMap<>();
        List<Deque<Integer>> stackList = new ArrayList<>();

        public void push(int val) {
            Integer time = cntMap.getOrDefault(val, 0);
            if (time == stackList.size()) {
                stackList.add(new ArrayDeque<>());
            }
            stackList.get(time).addLast(val);
            cntMap.put(val, time + 1);
        }

        public int pop() {
            int size = stackList.size();
            Integer ans = stackList.get(size - 1).pollLast();
            if (stackList.get(size - 1).isEmpty()) {
                stackList.remove(size - 1);
            }
            cntMap.put(ans, cntMap.get(ans) - 1);
            return ans;
        }
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);//堆栈为 [5]
        freqStack.push(7);//堆栈是 [5,7]
        freqStack.push(5);//堆栈是 [5,7,5]
        freqStack.push(7);//堆栈是 [5,7,5,7]
        freqStack.push(4);//堆栈是 [5,7,5,7,4]
        freqStack.push(5);//堆栈是 [5,7,5,7,4,5]
        freqStack.pop();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
        freqStack.pop();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
        freqStack.pop();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
        freqStack.pop();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
    }
}
