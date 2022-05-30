package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/validate-ip-address/
 */
public class Problem_0468_ValidateIpAddress {
    public static String validIPAddress(String queryIP) {
        if (queryIP == null || queryIP.length() == 0) {
            return "Neither";
        }

        if (queryIP.endsWith(".") || queryIP.endsWith(":")) {
            return "Neither";
        }

        if (queryIP.contains(".")) {
            String[] ip4 = queryIP.split("\\.");
            return checkIp4(ip4) ? "IPv4" : "Neither";
        } else if (queryIP.contains(":")) {
            String[] ip6 = queryIP.split(":");
            return checkIp6(ip6) ? "IPv6" : "Neither";
        } else {
            return "Neither";
        }
    }

    private static boolean checkIp6(String[] ip6) {
        if (ip6.length != 8) {
            return false;
        }
        String regex = "^[A-Fa-f0-9]+$";
        for (int i = 0; i < 8; i++) {
            if (ip6[i].length() == 0 || ip6[i].length() > 4) {
                return false;
            }
            if (!ip6[i].matches(regex)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIp4(String[] ip4) {
        if (ip4.length != 4) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (ip4[i].length() == 0 || ip4[i].length() > 3) {
                return false;
            }
            if (ip4[i].length() != 1 && ip4[i].startsWith("0")) {
                return false;
            }
            for (int j = 0; j < ip4[i].length(); j++) {
                if (!(ip4[i].charAt(j) >= '0' && ip4[i].charAt(j) <= '9')) {
                    return false;
                }

            }
            if (ip4[i].compareTo("255") > 0) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        // 2001:db8:85a3:0:0:8A2E:0370:7334
        // 20EE:FGb8:85a3:0:0:8A2E:0370:7334
        String ip = "20EE:FGb8:85a3:0:0:8A2E:0370:7334";
        String[] split = ip.split("\\.");
        System.out.println(Arrays.toString(split));
        System.out.println(validIPAddress(ip));
    }
}
