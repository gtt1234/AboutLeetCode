package com.tt.leetcode.easy.stack;

import java.util.*;

public class Isvalid {

    //一开始用左右两个指针匹配，这种方法无法解决这种"{[()][()]}"
    //记得这种题目在数据结构的书里其实提到过，用栈这种数据结构可以很方便的解决这个问题
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        Map<Character, Character> content = new HashMap<Character, Character>() {{
            put('(', ')');
            put('{', '}');
            put('[', ']');
        }};
        Stack stack = new Stack();
        int length = s.length();
        //官方里就不需要这个，想想怎样再相同思路下代码呈现的更简洁
        List<Character> remainStr = new ArrayList<>();
        char ch;
        for (int i = 0; i < length; i++) {
            //左括号入栈
            ch = s.charAt(i);
            if (content.get(ch) != null) {
                stack.push(s.charAt(i));
            } else {
                //右括号如果匹配上就出栈
                //对比官方给出的解答，其实这里不匹配可以直接return false了
                if (!stack.empty() && content.get(stack.elementAt(stack.size() - 1)).equals(ch)) {
                    stack.pop();
                    continue;
                }
                if (ch != ' ') {
                    remainStr.add(ch);
                }
            }
        }
        if (stack.size() == 0 && remainStr.size() == 0) {
            return true;
        }
        return false;
    }

    //官方解答，都是用栈这种数据结构，但是写法可参考，怎样使代码更简洁，避免不必要的变量，占用内存
    public boolean isValidBest(String s) {
        Map<Character, Character> mappings = new HashMap<Character, Character>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(new Isvalid().isValid("[({(())}[()])]"));
        System.out.println(new Isvalid().isValidBest("[({(())}[()])]"));

    }
}
