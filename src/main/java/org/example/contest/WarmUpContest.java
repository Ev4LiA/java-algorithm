package org.example.contest;

import java.util.*;

public class WarmUpContest {
    // 386. Lexicographical Numbers
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int curNum = 1;
        while (true) {
            res.add(curNum);
            if (curNum * 10 <= n) {
                curNum *= 10;
            } else {
                while (curNum > 0 && (curNum % 10 == 9 || curNum + 1 > n)) {
                    curNum = curNum / 10;
                }
                if (curNum == 0) break;
                curNum++;
            }
        }
        return res;
    }

    // 387. First Unique Character in a String
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    // 388. Longest Absolute File Path
    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        int maxLen = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf('\t') + 1;
            while (level + 1 < stack.size()) stack.pop();
            int len = stack.peek() + s.length() - level + 1;
            stack.push(len);
            if(s.contains(".")) maxLen = Math.max(maxLen, len-1);
        }
        return maxLen;
    }
}
