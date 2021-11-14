package com.testing.class18;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Test
 * @Description 类型说明
 * @Date 2021/10/7 23:44
 * @Created by zhangwenliang
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabdefghe1234g21"));


    }

    public static int lengthOfLongestSubstring(String s) {

        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;

    }

}



