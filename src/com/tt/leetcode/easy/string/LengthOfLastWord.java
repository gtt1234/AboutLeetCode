package com.tt.leetcode.easy.string;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 */
public class LengthOfLastWord {
    //从右向左遍历,遇到不是空格的+1,如果下一个是空格或者已经是第一位则结束
    //比下面的方法多了些判断,我应该学会反方向思考,遇到是空格的怎么样
    //整体思路是差不多的
    public int lengthOfLastWord(String s) {
        int lastLen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                lastLen++;
                if (lastLen != 0 && (i == 0 || s.charAt(i - 1) == ' ')) {
                    break;
                }
            }
        }
        return lastLen;
    }

    //从右向左遍历，从第一个不是空格的字符开始计数，一旦开始计数，再遇到空格就结束了
    public int lengthOfLastWordBest(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (count == 0) continue;
                break;
            }
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord().lengthOfLastWord("Hello world"));
        System.out.println(new LengthOfLastWord().lengthOfLastWord("a"));
        System.out.println(new LengthOfLastWord().lengthOfLastWord("a b c d "));
    }
}
