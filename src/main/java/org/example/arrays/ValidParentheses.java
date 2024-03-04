package org.example.arrays;

import java.util.ArrayList;

public class ValidParentheses {
    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        ArrayList<Character> stack = new ArrayList<Character>();

        for (char c : charArray) {
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else {
                if (stack.isEmpty()) return false;

                if (c == ')') {
                    if (stack.get(stack.size() - 1) == '(') {
                        stack.remove(stack.size() - 1);
                    } else {
                        return false;
                    }
                } else if (c == ']') {
                    if (stack.get(stack.size() - 1) == '[') {
                        stack.remove(stack.size() - 1);
                    } else {
                        return false;
                    }
                } else {
                    if (stack.get(stack.size() - 1) == '{') {
                        stack.remove(stack.size() - 1);
                    } else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}
