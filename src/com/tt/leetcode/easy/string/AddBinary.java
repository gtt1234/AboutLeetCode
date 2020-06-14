package com.tt.leetcode.easy.string;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int maxLen = Math.max(a.length(), b.length());
        int minLen = Math.min(a.length(), b.length());
        //将位数小的前面补0
        if (maxLen != minLen) {
            StringBuilder ssb = new StringBuilder();
            int len = maxLen - minLen;
            while (len > 0) {
                ssb.insert(0, "0");
                len--;
            }
            if (a.length() < maxLen) {
                a = ssb.toString() + a;
            } else {
                b = ssb.toString() + b;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        while (maxLen >= 1) {
            sum = Integer.parseInt(String.valueOf(a.charAt(maxLen - 1))) + Integer.parseInt(String.valueOf(b.charAt(maxLen - 1))) + sum;
            sb.insert(0, sum % 2);
            sum = sum / 2;
            maxLen--;
        }
        if (sum > 0) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    //同样的思路,前面补0的思想,但是实现起来更简单,
    public String addBinaryBetter(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;  // //是否进一位
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            //获取字符串a对应的某一位的值 当i<0是 sum+=0（向前补0） 否则取原值 ‘1’的char类型和‘0’的char类型刚好相差为1
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            //获取字符串a对应的某一位的值 当i<0是 sum+=0（向前补0） 否则取原值 ‘1’的char类型和‘0’的char类型刚好相差为1
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        // 判断最后一次计算是否有进位  有则在最前面加上1 否则原样输出
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }


    public static void main(String[] args) {
//        System.out.println(new AddBinary().addBinary("11", "1"));
//        System.out.println(new AddBinary().addBinary("1010", "1011"));
        System.out.println(new AddBinary().addBinary("1010110011100010000110100011001101110001111001001010001100000100011111011011111", "0"));
    }
}
