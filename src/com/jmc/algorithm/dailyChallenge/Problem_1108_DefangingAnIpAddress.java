package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/defanging-an-ip-address/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/21 11:50
 **/
public class Problem_1108_DefangingAnIpAddress {
    public static String defangIPaddr(String address) {
        // 输入：address = "1.1.1.1"
        // 输出："1[.]1[.]1[.]1"
        return address.replace(".", "[.]");
    }
}
