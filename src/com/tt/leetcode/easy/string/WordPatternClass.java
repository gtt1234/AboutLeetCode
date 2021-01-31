package com.tt.leetcode.easy.string;

import java.util.HashMap;

/**
 * @Description 单词规律
 * @Author gantt
 * @Date 2021/1/31 18:01
 */
public class WordPatternClass {

    public boolean wordPattern(String pattern, String s) {
        String[] s1 = s.split(" ");
        int l = pattern.length();
        if (s1.length != l) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < l; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(s1[i])) return false;
            } else {
                if (map.containsValue(s1[i])) return false;
            }

            map.put(c, s1[i]);
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(new WordPatternClass().wordPattern("abba", "dog cat cat dog"));


    }

}
