package com.tt.leetcode.easy.array;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonPrefix {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 示例 1:
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * 所有输入只包含小写字母 a-z 。
     */

    /**
     * 我的思路:
     * 1: 将数组里的每个字符串的第i个字符放到一个列表里
     * 2: 依次比较列表里所有字符是否相等,
     * 3: 相等的字符输出
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        List<Character> contain = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        boolean isCommon = true;
        int i = 0;
        while (isCommon) {
            for (String s : strs) {
                if (s == null || s.length() == 0 || s.length() < i + 1) {
                    isCommon = false;
                    break;
                }
                contain.add(s.charAt(i));
            }
            if (isCommon) {
                isCommon = isCommonChar(contain);
            }
            if (isCommon) {
                sb.append(contain.get(0));
            }
            i++;
            contain.clear();
        }
        return sb.toString();
    }

    public boolean isCommonChar(List<Character> chars) {
        char c = chars.get(0);
        boolean allCommon = chars.stream().allMatch(ch -> ch == c);
        return allCommon;
    }

    /**
     * https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode/
     * 优秀解法1
     * 水平扫描法
     *  第一个字符串与第二个字符串比较得出最长公共子串,(巧妙运用indexOf找出最长公共子串)
     *  再把得出的最长公共子串与第三个字符串比较得出最长公共子串,
     *  继续与后面的字符串比较
     *
     *  复杂度分析
     * 时间复杂度：O(S)，S 是所有字符串中字符数量的总和。
     * 最坏的情况下，n个字符串都是相同的。算法会将S1与其他字符串[S2…Sn] 都做一次比较。这样就会进行 S 次字符比较，其中 S 是输入数据中所有字符数量。
     * 空间复杂度：O(1)，我们只需要使用常数级别的额外空间。
     */
    public String longestCommonPrefixBest1(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args) {
       LongestCommonPrefix testClass = new LongestCommonPrefix();
       String[] strs = new String[]{"leet","lee","leed"};
       String s = testClass.longestCommonPrefixBest1(strs);
        System.out.println(s);
    }

    /**
     * 跟我的思路是一样的,但是这个代码简洁啊
     * 垂直扫描
     *
     * 同样是先比较每个字符串相同列上的字符（即不同字符串相同下标的字符）然后再进行对下一列的比较。
     *
     * 思考:我的输出是用加的方式,有相等的就加到一个空集合里,这样会浪费空间
     * 要学会用减的方式输出
     * 比如strs[0].substring(0, i)就是在原来的基础上减
     */
    public String longestCommonPrefixBest2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * 分治
     * 我们使用分治的技巧，将原问题 LCP(S i	⋯S j) 分成两个子问题LCP(S i⋯Smid ) 与 LCP(S_{mid+1},S_j)，
     * 其中 mid =(i+j)/2。 我们用子问题的解 lcpLeft 与 lcpRight 构造原问题的解 LCP(Si ⋯S j)。
     * 从头到尾挨个比较 lcpLeft 与 lcpRight 中的字符，直到不能再匹配为止。
     * 计算所得的 lcpLeft 与 lcpRight 最长公共前缀就是原问题的解 LCP(S i⋯S j) 。
     *
     * 分治法道理都懂,就是一个问题分成若干个相同的子问题,用子问题的解去构造原问题的解
     * 就是单看代码有点看不懂,要划划才懂,
     *
     */

    public String longestCommonPrefixBest3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        else {
            int mid = (l + r)/2;
            String lcpLeft =   longestCommonPrefix(strs, l , mid);
            String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    String commonPrefix(String left,String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if ( left.charAt(i) != right.charAt(i) )
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }




}
