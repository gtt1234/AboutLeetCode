package com.tt.leetcode.easy.string;

public class Strstr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.equalsIgnoreCase("")) {
            return 0;
        }
        int i = 0;
        while (haystack.length() >= needle.length()) {
            if (haystack.startsWith(needle)) {
                return i;
            } else {
                haystack = haystack.length() == 1 ? "" : haystack.substring(1);
            }
            i++;
        }
        return -1;
    }
}
