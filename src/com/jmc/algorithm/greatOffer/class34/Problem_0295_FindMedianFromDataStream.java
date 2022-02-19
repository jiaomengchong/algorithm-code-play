package com.jmc.algorithm.greatOffer.class34;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流的中位数
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/23 16:23
 */
public class Problem_0295_FindMedianFromDataStream {
    public static class smallComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static class bigComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static class MedianFinder {
        private PriorityQueue<Integer> pqMax;
        private PriorityQueue<Integer> pqMin;

        public MedianFinder() {
            pqMax = new PriorityQueue<>(new bigComparator());
            pqMin = new PriorityQueue<>(new smallComparator());
        }

        public void addNum(int num) {
            if (pqMax.isEmpty() || pqMax.peek() >= num) {
                pqMax.add(num);
            } else {
                pqMin.add(num);
            }
            // 调整平衡
            balance();
        }

        public double findMedian() {
            if (pqMax.size() == pqMin.size()) {
                return (double) (pqMax.peek() + pqMin.peek()) / 2;
            } else {
                boolean isBig = pqMax.size() > pqMin.size() ? true : false;
                return isBig ? pqMax.peek() : pqMin.peek();
            }
        }

        private void balance() {
            if (Math.abs(pqMax.size() - pqMin.size()) == 2) {
                boolean isBig = pqMax.size() > pqMin.size() ? true : false;
                if (isBig) {
                    pqMin.add(pqMax.poll());
                } else {
                    pqMax.add(pqMin.poll());
                }
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}
