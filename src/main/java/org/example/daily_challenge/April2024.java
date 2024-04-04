package org.example.daily_challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class April2024 {
    // 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        String[] parts = s.split("\\s+");
        return parts[parts.length - 1].length();
    }

    // 79. Word Search
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j] && search(board, i, j, 0, visited, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, int i, int j, int index, boolean[][] visited, String word) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
            return false;
        }

        if (word.charAt(index) != board[i][j]) return false;
        if (index == word.length() - 1) return true;

        visited[i][j] = true;
        if (search(board, i + 1, j, index + 1, visited, word)
                || search(board, i - 1, j, index + 1, visited, word)
                || search(board, i, j + 1, index + 1, visited, word)
                || search(board, i, j - 1, index + 1, visited, word)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    // 205. Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                if (!map.containsValue(t.charAt(i))) {
                    map.put(s.charAt(i), t.charAt(i));
                } else {
                    return false;
                }
            } else {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 1614. Maximum Nesting Depth of the Parentheses
    public int maxDepth(String s) {
        int depth = 0, max = Integer.MIN_VALUE;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth += 1;
            } else if (c == ')') {
                depth -= 1;
            }
            max = Math.max(depth, max);
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
