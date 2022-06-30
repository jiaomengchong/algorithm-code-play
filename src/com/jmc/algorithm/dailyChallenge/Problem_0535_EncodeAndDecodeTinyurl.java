package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/encode-and-decode-tinyurl/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/29 16:09
 **/
public class Problem_0535_EncodeAndDecodeTinyurl {
    public class Codec {
        private int id = 1;
        private Map<String, String> encodeMap = new HashMap<>();
        private Map<String, String> decodeMap = new HashMap<>();
        public String encode(String longUrl) {
            if (encodeMap.containsKey(longUrl)) {
                return encodeMap.get(longUrl);
            }
            String shortUrl = String.format("%s%s", "http://tinyurl.com/", id);
            encodeMap.put(longUrl, shortUrl);
            decodeMap.put(shortUrl, longUrl);
            id++;
            return shortUrl;
        }

        public String decode(String shortUrl) {
            return decodeMap.get(shortUrl);
        }
    }
}
