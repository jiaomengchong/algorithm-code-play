package com.jmc.algorithm.greatOffer.class19;

import java.util.LinkedList;

/**
 * 一张扑克有3个属性，每种属性有3种值（A、B、C）
 * 比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A
 * 比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A
 * 给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克
 * 从中挑选三张扑克，一个属性达标的条件是：这个属性在三张扑克中全一样，或全不一样
 * 挑选的三张扑克达标的要求是：每种属性都满足上面的条件
 * 比如："ABC"、"CBC"、"BBC"
 * 第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样
 * 第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样
 * 第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样
 * 每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标
 * 返回在cards[]中任意挑选三张扑克，达标的方法数
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/6 18:40
 */
public class Code05_CardsProblem {
    public static int ways0(String[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        int[] counts = new int[27];
        char[] chars;
        for (int i = 0; i < cards.length; i++) {
            chars = cards[i].toCharArray();
            counts[(chars[0] - 'A') * 9 + (chars[1] - 'A') * 3 + (chars[2] - 'A')]++;
        }

        int ans = 0;
        for (int i = 0; i < 27; i++) {
            if (counts[i] > 2) {
                ans += counts[i] * (counts[i] - 1) * (counts[i] - 2) / 6;
            }
        }

        LinkedList<Integer> path = new LinkedList<>();
        for (int i = 0; i < 27; i++) {
            if (counts[i] > 0) {
                path.addLast(i);
                ans += process0(counts, i, path);
                path.pollLast();
            }
        }
        return ans;
    }

    // pre 前面选的牌，一定要从小到大选择，否则会重复计算
    private static int process0(int[] counts, int pre, LinkedList<Integer> path) {
        if (path.size() == 3) {
            return getWays0(counts, path);
        }

        int ways = 0;
        for (int i = pre + 1; i < 27; i++) {
            if (counts[i] > 0) {
                path.addLast(i);
                ways += process0(counts, i, path);
                path.pollLast();
            }
        }
        return ways;
    }

    private static int getWays0(int[] counts, LinkedList<Integer> path) {
        // CCC 26
        Integer card1 = path.get(0);
        Integer card2 = path.get(1);
        Integer card3 = path.get(2);
        for (int i = 9; i > 0; i /= 3) {
            int cur1 = card1 / i;
            int cur2 = card2 / i;
            int cur3 = card3 / i;
            card1 %= i;
            card2 %= i;
            card3 %= i;
            if ((cur1 == cur2 && cur1 == cur3) || (cur1 != cur2 && cur1 != cur3 && cur2 != cur3)) {
                continue;
            }
            return 0;
        }

        card1 = path.get(0);
        card2 = path.get(1);
        card3 = path.get(2);

        return counts[card1] * counts[card2] * counts[card3];
    }

    public static void main(String[] args) {
        String[] cards = new String[]{
                "CCB",
                "BAB",
                "CCA",
                "AAA",
                "BBC",
                "BAA",
                "BAC",
                "ABA"};

        System.out.println(ways0(cards));
    }
}
